package com.fast.summary;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;

import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ProcessUtil {

    /**
     * 启动一个新的进程，并返回进程消息
     *
     * @param commands 启动命令
     * @return 进程消息列表
     */
    public static List<String> runProcess(String[] commands) throws Exception {
        if (ArrayUtils.isEmpty(commands)) {
            log.warn("No commands to run");
            return null;
        }

        log.info("Process commands");
        for (String command : commands) {
            log.info("command parameter: {}", command);
        }

        List<String> processMessages = new ArrayList<>();
        ProcessBuilder processBuilder = new ProcessBuilder(commands);
        long startTime = System.currentTimeMillis();
        Process process = processBuilder.start();

        try (InputStreamReader inputStreamReader = new InputStreamReader(process.getInputStream());
             LineNumberReader lineNumberReader = new LineNumberReader(inputStreamReader)) {
            String line;
            while ((line = lineNumberReader.readLine()) != null) {
                if (log.isTraceEnabled()) {
                    log.trace("Run command message: {}", line);
                }
                processMessages.add(line);
            }
        }
        int exitValue = process.waitFor();
        log.info("Process exits with {}, it cost {} ms", exitValue, System.currentTimeMillis() - startTime);
        return processMessages;
    }
}
