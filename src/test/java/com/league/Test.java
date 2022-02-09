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

import org.apache.http.HttpEntity;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.BeforeClass;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;

/**
 * @author Yewei Wang
 */
public class Test {
    // Apache HttpClient
    static CloseableHttpClient httpclient = HttpClients.createDefault();
    // String[0]: response status, String[1]: response body
    static ResponseHandler<String[]> responseHandler = response -> {
        int status = response.getStatusLine().getStatusCode();
        HttpEntity entity = response.getEntity();
        return entity != null ? new String[]{String.valueOf(status), EntityUtils.toString(entity)} : null;
    };

    /**
     * Test if web serve is working
     */
    @BeforeClass
    public static void testConnection() throws IOException {
        HttpUriRequest request = RequestBuilder.post("http://localhost:8080/").build();
        try {
            String[] responseBody = httpclient.execute(request, responseHandler);
            System.out.println("Server Status: " + responseBody[0]);
            assertEquals("200", responseBody[0]);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    /**
     *  Test Echo Matrix
     */
    @org.junit.Test
    public void testEcho() {
        try {
            File file = new File("./src/test/java/com/league/testFiles/matrix.csv");
            HttpEntity data = MultipartEntityBuilder.create().setMode(HttpMultipartMode.BROWSER_COMPATIBLE)
                    .addBinaryBody("file", file, ContentType.DEFAULT_BINARY, file.getName()).build();
            HttpUriRequest request = RequestBuilder.post("http://localhost:8080/echo").setEntity(data).build();
            String[] responseBody = httpclient.execute(request, responseHandler);
            System.out.println("Echo Status: " + responseBody[0]);
            System.out.println(responseBody[1]);
            assertEquals("200", responseBody[0]);
            assertEquals("1,2,3\n" + "4,5,6\n" + "7,8,9\n", responseBody[1]);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    /**
     *  Test Invert Matrix
     */
    @org.junit.Test
    public void testInvert() {
        try {
            File file = new File("./src/test/java/com/league/testFiles/matrix.csv");
            HttpEntity data = MultipartEntityBuilder.create().setMode(HttpMultipartMode.BROWSER_COMPATIBLE)
                    .addBinaryBody("file", file, ContentType.DEFAULT_BINARY, file.getName()).build();
            HttpUriRequest request = RequestBuilder.post("http://localhost:8080/invert").setEntity(data).build();
            String[] responseBody = httpclient.execute(request, responseHandler);
            System.out.println("Invert Status: " + responseBody[0]);
            System.out.println(responseBody[1]);
            assertEquals("200", responseBody[0]);
            assertEquals("1,4,7\n" + "2,5,8\n" + "3,6,9\n", responseBody[1]);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    /**
     *  Test Flatten Matrix
     */
    @org.junit.Test
    public void testFlatten() {
        try {
            File file = new File("./src/test/java/com/league/testFiles/matrix.csv");
            HttpEntity data = MultipartEntityBuilder.create().setMode(HttpMultipartMode.BROWSER_COMPATIBLE)
                    .addBinaryBody("file", file, ContentType.DEFAULT_BINARY, file.getName()).build();
            HttpUriRequest request = RequestBuilder.post("http://localhost:8080/flatten").setEntity(data).build();
            String[] responseBody = httpclient.execute(request, responseHandler);
            System.out.println("Flatten Status: " + responseBody[0]);
            System.out.println(responseBody[1]);
            assertEquals("200", responseBody[0]);
            assertEquals("1,2,3,4,5,6,7,8,9\n", responseBody[1]);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    /**
     *  Test calculate sum of Matrix
     */
    @org.junit.Test
    public void testSum() {
        try {
            File file = new File("./src/test/java/com/league/testFiles/matrix.csv");
            HttpEntity data = MultipartEntityBuilder.create().setMode(HttpMultipartMode.BROWSER_COMPATIBLE)
                    .addBinaryBody("file", file, ContentType.DEFAULT_BINARY, file.getName()).build();
            HttpUriRequest request = RequestBuilder.post("http://localhost:8080/sum").setEntity(data).build();
            String[] responseBody = httpclient.execute(request, responseHandler);
            System.out.println("Sum Status: " + responseBody[0]);
            System.out.println(responseBody[1]);
            assertEquals("200", responseBody[0]);
            assertEquals("45\n", responseBody[1]);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    /**
     *  Test calculate multiply result of Matrix
     */
    @org.junit.Test
    public void testMultiply() {
        try {
            File file = new File("./src/test/java/com/league/testFiles/matrix.csv");
            HttpEntity data = MultipartEntityBuilder.create().setMode(HttpMultipartMode.BROWSER_COMPATIBLE)
                    .addBinaryBody("file", file, ContentType.DEFAULT_BINARY, file.getName()).build();
            HttpUriRequest request = RequestBuilder.post("http://localhost:8080/multiply").setEntity(data).build();
            String[] responseBody = httpclient.execute(request, responseHandler);
            System.out.println("Multiply Status: " + responseBody[0]);
            System.out.println(responseBody[1]);
            assertEquals("200", responseBody[0]);
            assertEquals("362880\n", responseBody[1]);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    /**
     *  Test invert a single-value Matrix
     */
    @org.junit.Test
    public void testSingle() {
        try {
            File file = new File("./src/test/java/com/league/testFiles/matrixSingle.csv");
            HttpEntity data = MultipartEntityBuilder.create().setMode(HttpMultipartMode.BROWSER_COMPATIBLE)
                    .addBinaryBody("file", file, ContentType.DEFAULT_BINARY, file.getName()).build();
            HttpUriRequest request = RequestBuilder.post("http://localhost:8080/invert").setEntity(data).build();
            String[] responseBody = httpclient.execute(request, responseHandler);
            System.out.println("Single Status: " + responseBody[0]);
            System.out.println(responseBody[1]);
            assertEquals("200", responseBody[0]);
            assertEquals("1\n", responseBody[1]);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    /**
     *  Test invert an empty Matrix
     */
    @org.junit.Test
    public void testEmptyCsv() {
        try {
            File file = new File("./src/test/java/com/league/testFiles/matrixEmpty.csv");
            HttpEntity data = MultipartEntityBuilder.create().setMode(HttpMultipartMode.BROWSER_COMPATIBLE)
                    .addBinaryBody("file", file, ContentType.DEFAULT_BINARY, file.getName()).build();
            HttpUriRequest request = RequestBuilder.post("http://localhost:8080/echo").setEntity(data).build();
            String[] responseBody = httpclient.execute(request, responseHandler);
            System.out.println("EmptyCsv Status: " + responseBody[0]);
            System.out.println(responseBody[1]);
            assertEquals("400", responseBody[0]);
            assertEquals("Empty Matrix\n", responseBody[1]);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    /**
     *  Test invert a invalid Matrix
     */
    @org.junit.Test
    public void testNonSquareCsv() {
        try {
            File file = new File("./src/test/java/com/league/testFiles/matrixInvalid.csv");
            HttpEntity data = MultipartEntityBuilder.create().setMode(HttpMultipartMode.BROWSER_COMPATIBLE)
                    .addBinaryBody("file", file, ContentType.DEFAULT_BINARY, file.getName()).build();
            HttpUriRequest request = RequestBuilder.post("http://localhost:8080/invert").setEntity(data).build();
            String[] responseBody = httpclient.execute(request, responseHandler);
            System.out.println("NonSquareCsv Status: " + responseBody[0]);
            System.out.println(responseBody[1]);
            assertEquals("400", responseBody[0]);
            assertEquals("Invalid Matrix (must be square)\n", responseBody[1]);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    /**
     *  Test if an Integer overflow in Matrix
     */
    @org.junit.Test
    public void testIntegerOverflow() {
        try {
            File file = new File("./src/test/java/com/league/testFiles/matrixIntegerOverflow.csv");
            HttpEntity data = MultipartEntityBuilder.create().setMode(HttpMultipartMode.BROWSER_COMPATIBLE)
                    .addBinaryBody("file", file, ContentType.DEFAULT_BINARY, file.getName()).build();
            HttpUriRequest request = RequestBuilder.post("http://localhost:8080/flatten").setEntity(data).build();
            String[] responseBody = httpclient.execute(request, responseHandler);
            System.out.println("IntegerOverflow Status: " + responseBody[0]);
            System.out.println(responseBody[1]);
            assertEquals("400", responseBody[0]);
            assertEquals("Integer overflow\n", responseBody[1]);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    /**
     *  Test if sum result overflow in Matrix
     */
    @org.junit.Test
    public void testAddOverflow() {
        try {
            File file = new File("./src/test/java/com/league/testFiles/matrixAddOverflow.csv");
            HttpEntity data = MultipartEntityBuilder.create().setMode(HttpMultipartMode.BROWSER_COMPATIBLE)
                    .addBinaryBody("file", file, ContentType.DEFAULT_BINARY, file.getName()).build();
            HttpUriRequest request = RequestBuilder.post("http://localhost:8080/sum").setEntity(data).build();
            String[] responseBody = httpclient.execute(request, responseHandler);
            System.out.println("AddOverflow Status: " + responseBody[0]);
            System.out.println(responseBody[1]);
            assertEquals("400", responseBody[0]);
            assertEquals("Integer overflow during adding\n", responseBody[1]);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    /**
     *  Test if multiply result overflow in Matrix
     */
    @org.junit.Test
    public void testMultiplyOverflow() {
        try {
            File file = new File("./src/test/java/com/league/testFiles/matrixMultiplyOverflow.csv");
            HttpEntity data = MultipartEntityBuilder.create().setMode(HttpMultipartMode.BROWSER_COMPATIBLE)
                    .addBinaryBody("file", file, ContentType.DEFAULT_BINARY, file.getName()).build();
            HttpUriRequest request = RequestBuilder.post("http://localhost:8080/multiply").setEntity(data).build();
            String[] responseBody = httpclient.execute(request, responseHandler);
            System.out.println("MultiplyOverflow Status: " + responseBody[0]);
            System.out.println(responseBody[1]);
            assertEquals("400", responseBody[0]);
            assertEquals("Integer overflow during multiplying\n", responseBody[1]);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

}
