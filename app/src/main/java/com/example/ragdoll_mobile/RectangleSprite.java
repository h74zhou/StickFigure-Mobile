package com.example.ragdoll_mobile;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;
import android.util.Log;

public class RectangleSprite extends Sprite {

    public float roundEdge;

    public RectangleSprite(int xPosition, int yPosition, int width, int height, float roundEdge, boolean oval_or_not, String body) {
        super();
        this.roundEdge = roundEdge;
        this.oval = oval_or_not;
        this.bodyPart = body;

        if (body == "head") {
            this.pivot.x = width/2;
            this.pivot.y = height;
        }

        this.initialize(xPosition, yPosition, width,height);
    }

    public void initialize(int xPosition, int yPosition, int width, int height) {
        rect = new RectF(xPosition, yPosition, xPosition + width, yPosition + height);
        // rect = new RectF(700, 500, 700+width, 500+height);
    }

    public boolean pointInside(PointF p) {

        Matrix fullTransform = this.getFullTransform();
        Matrix inverseTransform = new Matrix();

        if (fullTransform.invert(inverseTransform)) {
            // Everything is Good
            Log.d("herun: ", "Successfully Inverted");
        } else {
            Log.d("herun: ", "Failed to Invert");
        }

        PointF newPoint = new PointF(p.x, p.y);
        float[] pts = new float[2];
        pts[0] = newPoint.x;
        pts[1] = newPoint.y;
//        inverseTransform.transform(newPoint, newPoint);
        inverseTransform.mapPoints(pts);
        newPoint.x = pts[0];
        newPoint.y = pts[1];
//        Log.d("herun: ", "Mouse X Point (TRANSFORMED): " + newPoint.x);
//        Log.d("herun: ", "Mouse Y Point (TRANSFORMED): " + newPoint.y);

        if (rect.contains(newPoint.x, newPoint.y)) {
            Log.d("herun: ", "THIS INVERSION WORKEDDDDD!");
        } else {
            Log.d("herun: ", "THIS INVERSION DID NOT WORK!!!");
        }
        return rect.contains(newPoint.x, newPoint.y);
    }

    public void drawSprite(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        if (oval) {
            Log.d("herun: ", "DREW OVALLLLLLL!!!!!!!");
            canvas.drawOval(rect, paint);
        } else {
            canvas.drawRoundRect(rect, roundEdge, roundEdge, paint);
        }

    }

    public String toString() {
        return "RectangleSprite: " + rect;
    }

}
