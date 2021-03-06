package org.fluentlenium.core.events;

import com.google.common.base.Function;
import org.fluentlenium.utils.ReflectionUtils;
import org.openqa.selenium.WebDriver;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class AnnotationScriptListener implements ScriptListener {
    private final Method method;
    private final Object container;
    private final String annotationName;

    AnnotationScriptListener(Method method, Object container, String annotationName) {
        this.method = method;
        this.container = container;
        this.annotationName = annotationName;
    }

    protected Function<Class<?>, Object> getArgsFunction(final String script, final WebDriver driver) {
        return new Function<Class<?>, Object>() {
            @Override
            public Object apply(Class<?> input) {
                if (input.isAssignableFrom(String.class)) {
                    return script;
                }
                if (input.isAssignableFrom(WebDriver.class)) {
                    return driver;
                }
                return null;
            }
        };
    }

    @Override
    public void on(String script, WebDriver driver) {
        Class<?>[] parameterTypes = method.getParameterTypes();

        Object[] args = ReflectionUtils.toArgs(getArgsFunction(script, driver), parameterTypes);

        try {
            ReflectionUtils.invoke(method, container, args);
        } catch (IllegalAccessException e) {
            throw new EventAnnotationsException("An error has occured in " + annotationName + " " + method, e);
        } catch (InvocationTargetException e) {
            if (e.getTargetException() instanceof RuntimeException) {
                throw (RuntimeException) e.getTargetException();
            } else if (e.getTargetException() instanceof Error) {
                throw (Error) e.getTargetException();
            }
            throw new EventAnnotationsException("An error has occured in " + annotationName + " " + method, e);
        }
    }
}
