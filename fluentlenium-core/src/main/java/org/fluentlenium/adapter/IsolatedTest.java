package org.fluentlenium.adapter;

/**
 * Instead of extending FluentTest, you can instantiate this class directly.
 * <p>
 * If you want to test concurrency, or if you need for any reason to not use JUnit nor TestNG,
 * you may use this class.
 * <p>
 * You should call {@link #quit()} manually to close the underlying webdriver.
 */
@SuppressWarnings("PMD.TestClassWithoutTestCases")
public class IsolatedTest extends FluentAdapter {

    public IsolatedTest() {
        initFluent(newWebDriver());
    }

    public void quit() {
        if (getDriver() != null) {
            getDriver().quit();
        }
        releaseFluent();
    }
}
