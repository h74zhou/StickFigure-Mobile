package com.example.ragdoll_mobile;


import java.util.Vector;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Spinner;

// Sprite Code taken from Jeff Avery Example
public abstract class Sprite {

    protected enum InteractionMode {
        IDLE,
        DRAGGING,
        SCALING,
        ROTATING
    }

    private Sprite parent = null;                                       // Pointer to our parent
    private Vector<Sprite> children = new Vector<Sprite>(); // Holds all of our children
    private Matrix transform = new Matrix();          // Our transformation matrix
    protected PointF lastPoint = null;                                 // Last mouse point
    protected InteractionMode interactionMode = InteractionMode.IDLE;   // current state

    public Sprite() {

    }

    public Sprite(Sprite parent) {
        if (parent != null) {
            parent.addChild(this);
        }
    }

    public void addChild(Sprite s) {
        children.add(s);
        s.setParent(this);
    }
    public Sprite getParent() {
        return parent;
    }
    private void setParent(Sprite s) {
        this.parent = s;
    }

    /**
     * Test whether a point, in world coordinates, is within our sprite.
     */
    public abstract boolean pointInside(PointF p);

    /** Handles Touch Event*/
    public void handleTouchEvent(MotionEvent e) {
        float currentX = e.getX();
        float currentY = e.getY();
        lastPoint = new PointF(currentX, currentY);
    }

    /** Handles Dragging Event*/
    public void handleDragEvent(MotionEvent e) {
        Log.d("herun: ", "Handle Drag Event Called");
        float newX = e.getX();
        float newY = e.getY();
        float oldX = lastPoint.x;
        float oldY = lastPoint.y;

        float x_diff = newX - oldX;
        float y_diff = newY - oldY;
        transform.postTranslate(x_diff,y_diff);
        lastPoint = new PointF(newX,newY);
    }

    public void handleMouseUp(MotionEvent e) {
        interactionMode = InteractionMode.IDLE;
    }

    public Sprite getSpriteHit(MotionEvent e) {
        Log.d("herun: ", "getSpriteHit Called");
        for (Sprite sprite : children) {
            Sprite s = sprite.getSpriteHit(e);
            if (s != null) {
                return s;
            }
        }

        PointF tempPoint = new PointF(e.getX(), e.getY());
        if (this.pointInside(tempPoint)) {
            Log.d("herun: ", "getSpriteHit WORKED!!!");
            return this;
        }
        return null;
    }

    /**
     * Returns the full transform to this object from the root
     */
    public Matrix getFullTransform() {
        Matrix returnTransform = new Matrix();
        Sprite curSprite = this;
        while (curSprite != null) {
            returnTransform.postConcat(curSprite.getLocalTransform());
            curSprite = curSprite.getParent();
        }
        return returnTransform;
    }
    /**
     * Returns our local transform
     */
    public Matrix getLocalTransform() {
        Matrix newMatrix = new Matrix();
        newMatrix.set(transform);
        return newMatrix;
    }

    /**
     * Performs an arbitrary transform on this sprite
     */
    public void transform(Matrix t) {
        transform.preConcat(t);
    }

    public void draw(Canvas canvas) {
        // Set to our transform
        // Graphics2D g2 = (Graphics2D)g;
        // AffineTransform currentAT = g.getTransform();
        Matrix oldMatrix = canvas.getMatrix();

//        currentAT.concatenate(getFullTransform());
        // currentAT.concatenate(transform);
        //g2.setTransform(currentAT);
        oldMatrix.preConcat(getFullTransform());
        canvas.setMatrix(oldMatrix);

        // Draw the sprite (delegated to sub-classes)
        //this.drawSprite(g);
        this.drawSprite(canvas);

        // Restore original transform
        canvas.setMatrix(oldMatrix);
        // Draw children
        for (Sprite sprite : children) {
            sprite.draw(canvas);
        }
    }

    protected abstract void drawSprite(Canvas canvas);


}