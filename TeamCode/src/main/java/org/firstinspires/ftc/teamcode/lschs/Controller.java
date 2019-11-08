package org.firstinspires.ftc.teamcode.lschs;

import com.qualcomm.robotcore.hardware.Gamepad;

import java.util.Arrays;

public class Controller {

    final private static int NUM_KEYS = 20;

    private Gamepad gamepad;

    public enum Key {
        J_LEFT_X, J_LEFT_Y, J_RIGHT_X, J_RIGHT_Y, LT, RT, J_LEFT_DOWN, J_RIGHT_DOWN, A, B, X, Y, LB, RB, BACK, START,
        DPAD_UP, DPAD_DOWN, DPAD_LEFT, DPAD_RIGHT
    }

    public boolean[] states = new boolean[NUM_KEYS];
    public double[] values = new double[NUM_KEYS];
    final private static Key key_index[] = Key.values();

    public Controller(Gamepad gamepad) {
        this.gamepad = gamepad;
        // Initialize Arrays
        Arrays.fill(states, false);
        Arrays.fill(values, 0);
    }


    public double getValue(Key key) {
        return values[key.ordinal()];
    }

    public double getRawReading(Key key) {

        switch (key) {
            case J_LEFT_X:
                return gamepad.left_stick_x;
            case J_LEFT_Y:
                return gamepad.left_stick_y;
            case J_RIGHT_X:
                return gamepad.right_stick_x;
            case J_RIGHT_Y:
                return gamepad.right_stick_y;
            case LT:
                return gamepad.left_trigger;
            case RT:
                return gamepad.right_trigger;
            case A:
                return gamepad.a ? 1 : 0;
            case B:
                return gamepad.b ? 1 : 0;
            case X:
                return gamepad.x ? 1 : 0;
            case Y:
                return gamepad.y ? 1 : 0;
            case LB:
                return gamepad.left_bumper ? 1 : 0;
            case RB:
                return gamepad.right_bumper ? 1 : 0;
            case BACK:
                return gamepad.back ? 1 : 0;
            case START:
                return gamepad.start ? 1 : 0;
            case J_LEFT_DOWN:
                return gamepad.left_stick_button ? 1 : 0;
            case J_RIGHT_DOWN:
                return gamepad.right_stick_button ? 1 : 0;
            case DPAD_RIGHT:
                return gamepad.dpad_right? 1 : 0;
            case DPAD_DOWN:
                return gamepad.dpad_down? 1 : 0;
            case DPAD_LEFT:
                return gamepad.dpad_left? 1 : 0;
            case DPAD_UP:
                return gamepad.dpad_up? 1 : 0;
            default:
                return 0;
        }
    }

    public boolean isKeyChanged(Key key) {
        return states[key.ordinal()];
    }

    public boolean isKeysChanged(Key... keys) {
        for (Key key : keys) {
            if (states[key.ordinal()] == true)
                return true;
        }
        return false;
    }

    public boolean isKeyHeld(Key key) {
        return getValue(key) > 0;
    }

    public boolean isGamepadChanged() {
        for (boolean i : states) {
            if (i) {
                return true;
            }
        }
        return false;
    }

    public boolean isKeyToggled(Key key) {
        return isKeyChanged(key) && (getValue(key) > 0);
    }

    public void fetchData() {

        for (int i = 0; i < NUM_KEYS; ++i) {

            double tempVal = getRawReading(key_index[i]);

            if (tempVal != values[i]) {
                states[i] = true;
                values[i] = tempVal;
            } else {
                states[i] = false;
            }
        }

    }
}
