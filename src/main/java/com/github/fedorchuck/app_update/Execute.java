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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Execute {
    private Log log = new Log(this.getClass());

    public void command(String command) {
        log.write("try to execute: "+command,Level.INFO);
        StringBuilder output = new StringBuilder();

        Process p;
        try {
            p = Runtime.getRuntime().exec(command);
            p.waitFor();
            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(p.getInputStream()));

            //noinspection UnusedAssignment
            String line = "";
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }

            log.write(output.toString(),Level.INFO);
        } catch (InterruptedException |
                IOException e) {
            log.write(e, Level.FATAL);
        }
    }
}
