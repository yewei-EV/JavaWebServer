package com.league;/*
 * Copyright (c) 2016 Tianbao Travel Ltd.
 * www.tianbaotravel.com
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Tianbao Travel Ltd. ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement you
 * entered into with Tianbao Travel Ltd.
 */

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author Yewei Wang
 */
public class FileUtil {

    public static List<List<Integer>> parseCsvFile(InputStream inputStream) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        int size = 0;
        List<List<Integer>> lines = new ArrayList<>();
        String readLine;
        while ((readLine = reader.readLine()) != null) {
            // Ignore header and footing
            if (readLine.isEmpty() || !Pattern.matches("[\\d,]+", readLine)) {
                continue;
            }
            String[] split = readLine.split(",");
            // Record length of matrix
            if (size == 0) size = split.length;
            if (split.length > 0 && split.length == size) {
                List<Integer> line = new ArrayList<>();
                for (String s : split) {
                    try {
                        line.add(Integer.valueOf(s));
                    } catch (Exception e) {
                        throw new NumberFormatException("Integer overflow");
                    }
                }
                lines.add(line);
            }
        }
        if (lines.size() == 0) throw new Exception("Empty Matrix");
        // Check if length == width
        if (lines.size() != lines.get(0).size()) throw new Exception("Invalid Matrix (must be square)");
        return lines;
    }

}
