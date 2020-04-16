package com.zwshao;

import com.zwshao.controller.FirstController;
import com.zwshao.test.annotation.TestTarget;
import com.zwshao.test.runner.SpringCoreRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SpringCoreRunner.class)
@TestTarget({FirstController.class})
public class ZwshaoRunner {

    @Test
    public void name() {
        System.out.println("hello world");
    }
}
