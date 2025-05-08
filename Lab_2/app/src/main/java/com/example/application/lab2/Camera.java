package com.example.application.lab2;

import android.opengl.Matrix;

public class Camera {
    private final float[] position = {0f, 1f, 5f};
    private final float[] front = {0f, 0f, -1f};
    private final float[] up = {0f, 1f, 0f};
    private float yaw = -90f;
    private float pitch = 0f;
    private final float[] viewMatrix = new float[16];
    private final float movementSpeed = 0.2f;

    public void processMovement(Direction dir) {
        float dx = front[0] * movementSpeed;
        float dz = front[2] * movementSpeed;
        switch (dir) {
            case FORWARD:
                position[0] += dx;
                position[2] += dz;
                break;
            case BACKWARD:
                position[0] -= dx;
                position[2] -= dz;
                break;
            case LEFT:
                position[0] -= dz;
                position[2] += dx;
                break;
            case RIGHT:
                position[0] += dz;
                position[2] -= dx;
                break;
        }
    }

    public float[] getViewMatrix() {
        float cx = position[0] + front[0];
        float cy = position[1] + front[1];
        float cz = position[2] + front[2];
        Matrix.setLookAtM(viewMatrix, 0,
                position[0], position[1], position[2],
                cx, cy, cz,
                up[0], up[1], up[2]);
        return viewMatrix;
    }

    public enum Direction { FORWARD, BACKWARD, LEFT, RIGHT }
}