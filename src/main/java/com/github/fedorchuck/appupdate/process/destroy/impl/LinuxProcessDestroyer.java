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

package com.github.fedorchuck.appupdate.process.destroy.impl;

import com.github.fedorchuck.appupdate.process.destroy.IProcessDestroyer;
import com.github.fedorchuck.appupdate.process.Utils;
import com.github.fedorchuck.appupdate.log.Log;

import java.io.IOException;
import java.util.List;

import static com.github.fedorchuck.appupdate.log.Level.*;

public class LinuxProcessDestroyer implements IProcessDestroyer {
    private Runtime rt = Runtime.getRuntime();
    private Log log = new Log(this.getClass());

    @Override
    public void killByIdList(List<Integer> pid) throws IOException {
        for (int id : pid)
            killById(id);
    }

    @Override
    public List<Integer> getProcessIdentifierList(String processNameToKill) throws IOException {
        log.write("try get list process with name: " + processNameToKill, INFO);

        Process process = rt.exec("ps -e");

        return Utils.read(process, processNameToKill);
    }

    @Override
    public void killById(int pid) throws IOException {
        log.write("try to kill process: " + pid, INFO);
        rt.exec("kill -9 " + pid);
    }
}
