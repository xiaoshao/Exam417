package com.zwshao.test.context;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.CacheAwareContextLoaderDelegate;
import org.springframework.test.context.MergedContextConfiguration;
import org.springframework.test.context.TestContext;
import org.springframework.util.Assert;

import java.lang.reflect.Method;

public class CoreTestContext implements TestContext {
    private final MergedContextConfiguration mergedContextConfiguration;

    private final CacheAwareContextLoaderDelegate cacheAwareContextLoaderDelegate;

    public CoreTestContext(MergedContextConfiguration mergedContextConfiguration,
                           CacheAwareContextLoaderDelegate cacheAwareContextLoaderDelegate) {
        this.mergedContextConfiguration = mergedContextConfiguration;
        this.cacheAwareContextLoaderDelegate = cacheAwareContextLoaderDelegate;
    }


    @Override
    public ApplicationContext getApplicationContext() {
        ApplicationContext context = this.cacheAwareContextLoaderDelegate.loadContext(this.mergedContextConfiguration);
        if (context instanceof ConfigurableApplicationContext) {
            ConfigurableApplicationContext cac = (ConfigurableApplicationContext)context;
            Assert.state(cac.isActive(), () -> {
                return "The ApplicationContext loaded for [" + this.mergedContextConfiguration + "] is not active. This may be due to one of the following reasons: 1) the context was closed programmatically by user code; 2) the context was closed during parallel test execution either according to @DirtiesContext semantics or due to automatic eviction from the ContextCache due to a maximum cache size policy.";
            });
        }

        return context;
    }

    @Override
    public Class<?> getTestClass() {
        return null;
    }

    @Override
    public Object getTestInstance() {
        return null;
    }

    @Override
    public Method getTestMethod() {
        return null;
    }

    @Override
    public Throwable getTestException() {
        return null;
    }

    @Override
    public void markApplicationContextDirty(DirtiesContext.HierarchyMode hierarchyMode) {

    }

    @Override
    public void updateState(Object o, Method method, Throwable throwable) {

    }

    @Override
    public void setAttribute(String s, Object o) {

    }

    @Override
    public Object getAttribute(String s) {
        return null;
    }

    @Override
    public Object removeAttribute(String s) {
        return null;
    }

    @Override
    public boolean hasAttribute(String s) {
        return false;
    }

    @Override
    public String[] attributeNames() {
        return new String[0];
    }
}
