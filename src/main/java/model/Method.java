package model;

public enum Method {
    SIMPSON,
    RECTANGLE,
    TRAPEZOID;


    @Override
    public String toString() {
        switch (this) {
            case RECTANGLE -> {
                return "Метод прямоугольников";
            }
            case SIMPSON -> {
                return "Метод Симпсона";
            }
            default -> {
                return "Метод трапеций";
            }
        }
    }
}
