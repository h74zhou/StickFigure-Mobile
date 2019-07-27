package com.example.ragdoll_mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Matrix;
import android.os.Bundle;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DrawingCanvas canvas = new DrawingCanvas(this);
        canvas.addSprite(this.makeSprite());
        setContentView(canvas);
    }

    private static Sprite makeSprite() {
        // Define All The Sprites
        Sprite torso = new RectangleSprite(0, 0, 200, 350, 20.0F, false, "torso");
        Sprite head = new RectangleSprite(0, 0, 150, 200, 200.0F, true, "head");
        Sprite leftUpperLeg = new RectangleSprite(0, 0, 50, 225, 20.0F, true, "leftupperleg");
        Sprite rightUpperLeg = new RectangleSprite(0, 0, 50, 225, 20.0F, true, "rightupperleg");
        Sprite leftLowerLeg = new RectangleSprite(0, 0, 50, 200, 20.0F, true, "leftlowerleg");
        Sprite rightLowerLeg = new RectangleSprite(0, 0, 50, 200, 20.0F, true, "rightlowerleg");
        Sprite leftFoot = new RectangleSprite(0, 0, 80, 35, 20.0F, true, "leftfoot");
        Sprite rightFoot = new RectangleSprite(0, 0, 80, 35, 20.0F, true, "rightfoot");

        // Translate Torso
        Matrix torsoMatrix = new Matrix();
        torsoMatrix.postTranslate(700, 500);
        torso.transform(torsoMatrix);

        // Translate Head
        Matrix headMatrix = new Matrix();
        headMatrix.postTranslate(30, -200);
        head.transform(headMatrix);

        // Translate Left Upper Leg
        Matrix leftUpperLegMatrix = new Matrix();
        leftUpperLegMatrix.postTranslate(10, 350);
        leftUpperLeg.transform(leftUpperLegMatrix);

        // Translate Right Upper Leg
        Matrix rightUpperLegMatrix = new Matrix();
        rightUpperLegMatrix.postTranslate(140, 350);
        rightUpperLeg.transform(rightUpperLegMatrix);

        // Translate Left Lower Leg
        Matrix leftLowerLegMatrix = new Matrix();
        leftLowerLegMatrix.postTranslate(0, 225);
        leftLowerLeg.transform(leftLowerLegMatrix);

        // Translate Right Lower Leg
        Matrix rightLowerLegMatrix = new Matrix();
        rightLowerLegMatrix.postTranslate(0, 225);
        rightLowerLeg.transform(rightLowerLegMatrix);

        // Translate Left Foot
        Matrix leftFootMatrix = new Matrix();
        leftFootMatrix.postTranslate(-50, 190);
        leftFoot.transform(leftFootMatrix);

        // Translate Right Foot
        Matrix rightFootMatrix = new Matrix();
        rightFootMatrix.postTranslate(20, 190);
        rightFoot.transform(rightFootMatrix);

        // Set Min/Max Rotation for head
        head.max_degree = 25;
        head.min_degree = -25;

        torso.addChild(head);
        torso.addChild(leftUpperLeg);
        torso.addChild(rightUpperLeg);
        leftUpperLeg.addChild(leftLowerLeg);
        rightUpperLeg.addChild(rightLowerLeg);
        leftLowerLeg.addChild(leftFoot);
        rightLowerLeg.addChild(rightFoot);
        return torso;
    }

}

