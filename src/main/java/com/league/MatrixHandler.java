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

import java.util.List;

/**
 * @author Yewei Wang
 */
public class MatrixHandler {

    public static StringBuilder echoMatrix(List<List<Integer>> matrix) {
        StringBuilder response = new StringBuilder();
        for (List<Integer> line : matrix) {
            for (int i = 0; i < line.size(); ++i) {
                if (i == line.size() - 1) {
                    response.append(line.get(i));
                } else {
                    response.append(line.get(i)).append(",");
                }
            }
            response.append("\n");
        }
        return response;
    }

    public static StringBuilder invertMatrix(List<List<Integer>> matrix) {
        StringBuilder response = new StringBuilder();
        for (int i = 0; i < matrix.size(); ++i) {
            for (int j = 0; j < matrix.size(); ++j) {
                // invert x and y
                if (j == matrix.size() - 1) {
                    response.append(matrix.get(j).get(i));
                } else {
                    response.append(matrix.get(j).get(i)).append(",");
                }
            }
            response.append("\n");
        }
        return response;
    }

    public static StringBuilder flattenMatrix(List<List<Integer>> matrix) {
        StringBuilder response = new StringBuilder();
        for (List<Integer> line : matrix) {
            for (Integer integer : line) {
                response.append(integer).append(",");
            }
        }
        return response;
    }

    public static StringBuilder sumMatrix(List<List<Integer>> matrix) {
        StringBuilder response = new StringBuilder();
        int sum = 0;
        for (List<Integer> line : matrix) {
            for (Integer integer : line) {
                try {
                    sum = Math.addExact(sum, integer);
                } catch (ArithmeticException e) {
                    throw new ArithmeticException("Integer overflow during adding");
                }
            }
        }
        response.append(sum).append("\n");
        return response;
    }

    public static StringBuilder multiplyMatrix(List<List<Integer>> matrix) {
        StringBuilder response = new StringBuilder();
        long multiply = 1;
        for (List<Integer> line : matrix) {
            for (Integer integer : line) {
                try {
                    multiply = Math.multiplyExact(multiply, integer);
                } catch (ArithmeticException e) {
                    throw new ArithmeticException("Integer overflow during multiplying");
                }
            }
        }
        response.append(multiply).append("\n");
        return response;
    }

}
