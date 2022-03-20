package io;

import model.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class MethodIOImpl implements MethodIO {

    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    Map<String, Function> functionMap = new HashMap<>();

    Map<String, Method> methodMap = new HashMap<>();

    Map<String, RectangleMethodType> rectangleMethodTypeMap = new HashMap<>();

    public MethodIOImpl() {
        functionMap.put("1", new Function("x^5 + 2*x^3 + 1.2x + 3"));
        functionMap.put("2", new Function("sin(x^3) + 1.7x + 3"));
        functionMap.put("3", new Function("sin(x + 3)"));
        functionMap.put("4", new Function("x^2"));
        functionMap.put("5", new Function("-2x^3-3x^2+x+5"));
        methodMap.put("1", Method.SIMPSON);
        methodMap.put("2", Method.RECTANGLE);
        methodMap.put("3", Method.TRAPEZOID);
        rectangleMethodTypeMap.put("1", RectangleMethodType.CENTER);
        rectangleMethodTypeMap.put("2", RectangleMethodType.LEFT);
        rectangleMethodTypeMap.put("3", RectangleMethodType.RIGHT);
    }

    @Override
    public InputDTO inputData() throws Exception {
        AtomicInteger cnt = new AtomicInteger(1);
        Function function;
        Method method;
        RectangleMethodType rectangleMethodType = RectangleMethodType.CENTER;
        double leftBound, rightBound, inaccuracy;
        System.out.println("Выберите функцию:");
        functionMap.keySet().forEach(x -> {
            System.out.println(cnt.getAndIncrement() + ") " + functionMap.get(x).getStringExpression());
        });
        cnt.set(1);
        try {
            function = functionMap.get(reader.readLine());
        } catch (Exception e) {
            throw new Exception("Ошибка. Неверный номер функции");
        }
        System.out.println("Выберите метод:");
        methodMap.keySet().forEach(x -> {
            System.out.println(cnt.getAndIncrement() + ") " + methodMap.get(x).toString());
        });
        try {
            method = methodMap.get(reader.readLine());
        } catch (Exception e) {
            throw new Exception("Ошибка. Неверный номер метода");
        }
        if (method == Method.RECTANGLE) {
            cnt.set(1);
            rectangleMethodTypeMap.keySet().forEach(x -> {
                System.out.println(cnt.getAndIncrement() + ") " + rectangleMethodTypeMap.get(x).toString());
            });
            try {
                rectangleMethodType = rectangleMethodTypeMap.get(reader.readLine());
            } catch (Exception e) {
                throw new Exception("Ошибка. Неверный номер типа подсчета");
            }
        }
        System.out.println("Введите левую границу:");
        try {
            leftBound = Double.parseDouble(reader.readLine());
        } catch (Exception e) {
            throw new Exception("Ошибка. Недопустимая левая граница");
        }
        System.out.println("Введите правую границу:");
        try {
            rightBound = Double.parseDouble(reader.readLine());
        } catch (Exception e) {
            throw new Exception("Ошибка. Недопустимая правая граница");
        }
        System.out.println("Введите максимальную погрешность:");
        try {
            inaccuracy = Double.parseDouble(reader.readLine());
        } catch (Exception e) {
            throw new Exception("Ошибка. Недопустимая погрешность");
        }
        return new InputDTO(inaccuracy, function, leftBound, rightBound, method, rectangleMethodType);
    }

    @Override
    public void printData(AnswerDTO answerDTO) {
        System.out.println("Вычисленное значение интеграла: " + answerDTO.getAnswer());
        System.out.println("Погрешность по правилу Рунге: " + answerDTO.getInaccuracy());
        System.out.println("Количество отрезков в разбиении: " + answerDTO.getN_RANGES());
    }
}
