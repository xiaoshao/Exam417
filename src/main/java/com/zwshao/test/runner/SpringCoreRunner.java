package com.zwshao.test.runner;

import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.InitializationError;
import org.springframework.test.context.TestContextManager;

public class SpringCoreRunner extends BlockJUnit4ClassRunner {

    private TestContextManager testContextManager;
    public SpringCoreRunner(Class<?> klass) throws InitializationError {
        super(klass);
    }



}
