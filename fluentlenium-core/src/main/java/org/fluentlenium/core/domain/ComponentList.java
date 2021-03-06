package org.fluentlenium.core.domain;

import lombok.experimental.Delegate;
import org.fluentlenium.core.FluentControl;
import org.fluentlenium.core.components.ComponentInstantiator;
import org.fluentlenium.core.components.LazyComponents;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ComponentList<T> extends DelegatingList<T> implements WrapsElements, LazyComponents {
    protected final Class<T> componentClass;

    protected final ComponentInstantiator instantiator;
    protected final FluentControl fluentControl;
    protected List<WebElement> proxy;

    @Delegate
    private LazyComponents lazyComponents = new NotLazyComponents();

    public ComponentList(Class<T> componentClass, List<T> list, FluentControl fluentControl, ComponentInstantiator instantiator) {
        super(list);
        if (list instanceof LazyComponents) {
            lazyComponents = (LazyComponents) list;
        }
        this.componentClass = componentClass;
        this.fluentControl = fluentControl;
        this.instantiator = instantiator;

        if (this.list instanceof WrapsElements) {
            setProxy(((WrapsElements) this.list).getWrappedElements());
        }
    }

    public void setProxy(List<WebElement> proxy) {
        this.proxy = proxy;
    }

    @Override
    public List<WebElement> getWrappedElements() {
        return this.proxy;
    }
}
