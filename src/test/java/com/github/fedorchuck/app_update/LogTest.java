/*
 * Copyright [2016] [Volodymyr Fedorchuk <vl.fedorchuck@gmail.com>]
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.fedorchuck.app_update;

import com.github.fedorchuck.app_update.log.Level;
import com.github.fedorchuck.app_update.log.Log;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class LogTest {

    @Test
    public void test01() {
        try {
            Log.write(this.getClass(), new IOException(), Level.INFO);
        } catch (Throwable e) {
            fail();
        }
    }

    @Test
    public void test02() {
        try {
            Log.write(this.getClass(), new IOException(),"test", Level.DEBUG);
        } catch (Throwable e) {
            fail();
        }
    }

}