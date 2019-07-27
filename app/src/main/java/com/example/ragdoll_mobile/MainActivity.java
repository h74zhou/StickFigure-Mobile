package com.example.ragdoll_mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Matrix;
import android.os.Bundle;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    public DrawingCanvas canvas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        canvas = new DrawingCanvas(this);
        canvas.addSprite(this.makeSprite());
        setContentView(canvas);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.top_menu, menu);
        return true;
    }

    @Override
    public  boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.reset) {
            Log.d("herunR:: ", "reset button pressed");
            canvas.sprites.clear();
            canvas.addSprite(this.makeSprite());
            setContentView(canvas);
            return true;
        }
        return super.onOptionsItemSelected(item);
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
        Sprite leftUpperArm = new RectangleSprite(0,0, 200, 50, 20.0F, true,"leftupperarm");
        Sprite rightUpperArm = new RectangleSprite(0,0, 200, 50, 20.0F, true,"rightupperarm");
        Sprite leftLowerArm = new RectangleSprite(0,0, 175, 50, 20.0F, true,"leftlowerarm");
        Sprite rightLowerArm = new RectangleSprite(0,0, 175, 50, 20.0F, true,"rightlowerarm");
        Sprite leftHand = new RectangleSprite(0, 0, 50, 50, 20.0F, true, "lefthand");;
        Sprite rightHand = new RectangleSprite(0, 0, 50, 50, 20.0F, true, "righthand");;

        // Translate Torso
        Matrix torsoMatrix = new Matrix();
        torsoMatrix.postTranslate(700, 500);
        torso.transform(torsoMatrix);

        // Translate Head
        Matrix headMatrix = new Matrix();
        headMatrix.postTranslate(30, -200);
        head.transform(headMatrix);

        // Translate Left hand
        Matrix leftHandMatrix = new Matrix();
        leftHandMatrix.postTranslate(-50, 0);
        leftHand.transform(leftHandMatrix);

        // Translate Right hand
        Matrix rightHandMatrix = new Matrix();
        rightHandMatrix.postTranslate(175, 0);
        rightHand.transform(rightHandMatrix);

        // Translate Left Upper Arm
        Matrix leftUpperArmMatrix = new Matrix();
        leftUpperArmMatrix.postTranslate(-200, 0);
        leftUpperArm.transform(leftUpperArmMatrix);

        // Translate Right Upper Arm
        Matrix rightUpperArmMatrix = new Matrix();
        rightUpperArmMatrix.postTranslate(200, 0);
        rightUpperArm.transform(rightUpperArmMatrix);

        // Translate Left Lower Arm
        Matrix leftLowerArmMatrix = new Matrix();
        leftLowerArmMatrix.postTranslate(-175, 0);
        leftLowerArm.transform(leftLowerArmMatrix);

        // Translate Right Lower Arm
        Matrix rightLowerArmMatrix = new Matrix();
        rightLowerArmMatrix.postTranslate(200, 0);
        rightLowerArm.transform(rightLowerArmMatrix);

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


        // Set Min/Max Rotation for all body parts
        head.max_degree = 50;
        head.min_degree = -50;
        leftUpperLeg.min_degree = -90;
        leftUpperLeg.max_degree = 90;
        rightUpperLeg.min_degree = -90;
        rightUpperLeg.max_degree = 90;
        leftLowerLeg.min_degree = -90;
        leftLowerLeg.max_degree = 90;
        rightLowerLeg.min_degree = -90;
        rightLowerLeg.max_degree = 90;
        leftFoot.min_degree = -35;
        leftFoot.max_degree = 35;
        rightFoot.max_degree = 35;
        rightFoot.min_degree = -35;
        leftLowerArm.min_degree = -135;
        leftLowerArm.max_degree = 135;
        rightLowerArm.min_degree = -135;
        rightLowerArm.max_degree = 135;
        leftHand.min_degree = -35;
        leftHand.max_degree = 35;
        rightHand.min_degree = -35;
        rightHand.max_degree = 35;
        //leftUpperArm.min_degree = -360;
        //leftUpperArm.max_degree = 360;

        // Define Torso's children
        torso.addChild(head);
        torso.addChild(leftUpperArm);
        torso.addChild(rightUpperArm);
        torso.addChild(leftUpperLeg);
        torso.addChild(rightUpperLeg);

        // Define Upper Arm Children
        leftUpperArm.addChild(leftLowerArm);
        rightUpperArm.addChild(rightLowerArm);

        // Define hand Relationship
        leftLowerArm.addChild(leftHand);
        rightLowerArm.addChild(rightHand);

        // Define Upper Leg Children
        leftUpperLeg.addChild(leftLowerLeg);
        rightUpperLeg.addChild(rightLowerLeg);
        leftLowerLeg.addChild(leftFoot);
        rightLowerLeg.addChild(rightFoot);
        return torso;
    }

}

