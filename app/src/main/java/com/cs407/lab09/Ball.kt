package com.cs407.lab09

import android.util.Log

/**
 * Represents a ball that can move. (No Android UI imports!)
 *
 * Constructor parameters:
 * - backgroundWidth: the width of the background, of type Float
 * - backgroundHeight: the height of the background, of type Float
 * - ballSize: the width/height of the ball, of type Float
 */
class Ball(
    private val backgroundWidth: Float,
    private val backgroundHeight: Float,
    private val ballSize: Float
) {
    var posX = backgroundWidth / 2
    var posY = backgroundHeight / 2
    var velocityX = 0f
    var velocityY = 0f
    private var accX = 0f
    private var accY = 0f

    private var isFirstUpdate = true


    init {
        reset()
    }

    /**
     * Updates the ball's position and velocity based on the given acceleration and time step.
     * (See lab handout for physics equations)
     */
    fun updatePositionAndVelocity(xAcc: Float, yAcc: Float, dT: Float) {
        if(isFirstUpdate) {
            isFirstUpdate = false
            accX = xAcc
            accY = yAcc
            return
        }
        checkBoundaries()
        Log.d("helloworld", "x: " + posX.toString() + ", y: " + posY.toString() )


        val oldAccX = accX
        val oldAccY = accY

        accX += ((xAcc - accX)) * (dT) * 50
        accY += ((yAcc - accY)) * (dT) * 50
        val oldVelocityX = velocityX
        val oldVelocityY = velocityY
        velocityX += (oldAccX * dT)
        velocityY += (oldAccY * dT)

        posX += (oldVelocityX * dT)
        posY += (oldVelocityY * dT)


    }

    /**
     * Ensures the ball does not move outside the boundaries.
     * When it collides, velocity and acceleration perpendicular to the
     * boundary should be set to 0.
     */
    fun checkBoundaries() {
        val rad = ballSize/2
        if(posX - rad < 0f){
            posX = 0.001f + rad
            velocityX = 0f
            accX = 0f
        } else if (posY - rad < 0f){
            posY = 0.001f + rad
            velocityY = 0f
            accY = 0f
        } else if (posY + rad > backgroundHeight){
            posY = backgroundHeight - (0.001f + rad)
            velocityY = 0f
            accY = 0f
        } else if (posX + rad > backgroundWidth){
            posX = backgroundWidth - (0.001f + rad)
            velocityX = 0f
            accX = 0f
        }
    }

    /**
     * Resets the ball to the center of the screen with zero
     * velocity and acceleration.
     */
    fun reset() {
        // TODO: implement the reset function
        // (Reset posX, posY, velocityX, velocityY, accX, accY, isFirstUpdate)
        posX = backgroundWidth / 2
        posY = backgroundHeight / 2
        velocityX = 0f
        velocityY = 0f
        accX = 0f
        accY = 0f
        isFirstUpdate = true

    }
}