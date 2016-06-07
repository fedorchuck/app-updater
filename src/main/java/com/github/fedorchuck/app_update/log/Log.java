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

package com.github.fedorchuck.app_update.log;

import java.io.*;
import java.util.Arrays;
import java.util.Date;

public class Log {
    private Class aClass;

    public Log(Class aClass) {
        this.aClass = aClass;
    }

    public void write(Throwable throwable, String message, Level level) {
        writeFile(
        '\t'+new Date().toString()+"\t["+this.aClass.getCanonicalName()+"]\t[" + level +"]\n"
        + "Info : " + message + "\n"
        + "StackTrace : " + Arrays.toString(throwable.getStackTrace()),level);
    }

    public void write(Throwable throwable, Level level) {
        write(throwable,throwable.getMessage() + '\t' + throwable.getCause(), level);
    }

    public void write(String message, Level level) {
        writeFile(
                '\t'+new Date().toString()+"\t["+this.aClass.getCanonicalName()+"]\t[" + level +"]\n"
                        + "Info : " + message,level);
    }

    private void writeFile(String content, Level level) {
        write(content,new File("log/"+level));
        write(content,new File("log/ALL"));
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    private void write(String content, File file) {

        System.out.println(file.getAbsolutePath());
        try {
            System.out.println(file.getCanonicalPath());

            if (!file.getParentFile().exists())
                file.getParentFile().mkdirs();
            if (!file.exists())
                file.createNewFile();

            //true = append file
            FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(content+'\n');

            bw.close();

        } catch (IOException ignore) { } //what can i do :)
    }
}
