package com.league;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.List;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/", new DefaultHandler());
        server.createContext("/echo", new EchoHandler());
        server.createContext("/invert", new InvertHandler());
        server.createContext("/flatten", new FlattenHandler());
        server.createContext("/sum", new SumHandler());
        server.createContext("/multiply", new MultiplyHandler());
        server.setExecutor(Executors.newCachedThreadPool());
        System.out.println("LeagueChallenge web server started.");
        server.start();
    }

    static class DefaultHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange httpExchange) throws IOException {
            String response = "Endpoints: \n" +
                    "curl -F 'file=@/path/matrix.csv' \"localhost:8080/echo\" \n" +
                    "curl -F 'file=@/path/matrix.csv' \"localhost:8080/invert\" \n" +
                    "curl -F 'file=@/path/matrix.csv' \"localhost:8080/flatten\" \n" +
                    "curl -F 'file=@/path/matrix.csv' \"localhost:8080/sum\" \n" +
                    "curl -F 'file=@/path/matrix.csv' \"localhost:8080/multiply\" \n";
            httpExchange.sendResponseHeaders(200, response.length());
            OutputStream os = httpExchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }

    static class EchoHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange httpExchange) throws IOException {
            try {
                List<List<Integer>> input = FileUtil.parseCsvFile(httpExchange.getRequestBody());
                StringBuilder response = MatrixHandler.echoMatrix(input);
                httpExchange.sendResponseHeaders(200, response.length());
                OutputStream os = httpExchange.getResponseBody();
                os.write(response.toString().getBytes());
                os.close();
            } catch (Exception e) {
                StringBuilder response = new StringBuilder();
                response.append(e.getMessage()).append("\n");
                httpExchange.sendResponseHeaders(400, response.length());
                OutputStream os = httpExchange.getResponseBody();
                os.write(response.toString().getBytes());
                os.close();
            }
        }
    }

    static class InvertHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange httpExchange) throws IOException {
            try {
                List<List<Integer>> input = FileUtil.parseCsvFile(httpExchange.getRequestBody());
                StringBuilder response = MatrixHandler.invertMatrix(input);
                httpExchange.sendResponseHeaders(200, response.length());
                OutputStream os = httpExchange.getResponseBody();
                os.write(response.toString().getBytes());
                os.close();
            } catch (Exception e) {
                StringBuilder response = new StringBuilder();
                response.append(e.getMessage()).append("\n");
                httpExchange.sendResponseHeaders(400, response.length());
                OutputStream os = httpExchange.getResponseBody();
                os.write(response.toString().getBytes());
                os.close();
            }
        }
    }

    static class FlattenHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange httpExchange) throws IOException {
            try {
                List<List<Integer>> input = FileUtil.parseCsvFile(httpExchange.getRequestBody());
                StringBuilder response = MatrixHandler.flattenMatrix(input);
                response.deleteCharAt(response.length() - 1).append("\n");
                httpExchange.sendResponseHeaders(200, response.length());
                OutputStream os = httpExchange.getResponseBody();
                os.write(response.toString().getBytes());
                os.close();
            } catch (Exception e) {
                StringBuilder response = new StringBuilder();
                response.append(e.getMessage()).append("\n");
                httpExchange.sendResponseHeaders(400, response.length());
                OutputStream os = httpExchange.getResponseBody();
                os.write(response.toString().getBytes());
                os.close();
            }
        }
    }
    static class SumHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange httpExchange) throws IOException {
            try {
                List<List<Integer>> input = FileUtil.parseCsvFile(httpExchange.getRequestBody());
                StringBuilder response = MatrixHandler.sumMatrix(input);
                httpExchange.sendResponseHeaders(200, response.length());
                OutputStream os = httpExchange.getResponseBody();
                os.write(response.toString().getBytes());
                os.close();
            } catch (Exception e) {
                StringBuilder response = new StringBuilder();
                response.append(e.getMessage()).append("\n");
                httpExchange.sendResponseHeaders(400, response.length());
                OutputStream os = httpExchange.getResponseBody();
                os.write(response.toString().getBytes());
                os.close();
            }
        }
    }
    static class MultiplyHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange httpExchange) throws IOException {
            try {
                List<List<Integer>> input = FileUtil.parseCsvFile(httpExchange.getRequestBody());
                StringBuilder response = MatrixHandler.multiplyMatrix(input);
                httpExchange.sendResponseHeaders(200, response.length());
                OutputStream os = httpExchange.getResponseBody();
                os.write(response.toString().getBytes());
                os.close();
            } catch (Exception e) {
                StringBuilder response = new StringBuilder();
                response.append(e.getMessage()).append("\n");
                httpExchange.sendResponseHeaders(400, response.length());
                OutputStream os = httpExchange.getResponseBody();
                os.write(response.toString().getBytes());
                os.close();
            }
        }
    }

}


