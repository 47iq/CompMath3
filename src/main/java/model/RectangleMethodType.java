package model;

public enum RectangleMethodType {
    LEFT,
    CENTER,
    RIGHT;

    @Override
    public String toString() {
        switch (this) {
            case LEFT -> {
                return "Левые границы отрезков";
            }
            case RIGHT -> {
                return "Правые границы отрезков";
            }
            default -> {
                return "Центры отрезков";
            }
        }
    }
}
