package org.firstinspires.ftc.teamcode.opmode.example;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.pedroPathing.localization.Pose;
import org.firstinspires.ftc.teamcode.pedroPathing.pathGeneration.MathFunctions;
import org.firstinspires.ftc.teamcode.pedroPathing.pathGeneration.Vector;
import org.firstinspires.ftc.teamcode.pedroPathing.follower.Follower;
import org.firstinspires.ftc.teamcode.config.subsystem.ClawSubsystem;

/**
 * This is an example teleop that showcases movement and control of three servos and field-centric driving.
 *
 * @author Baron Henderson - 20077 The Indubitables
 * @version 2.0, 9/8/2024
 */

@TeleOp(name = "Example Field-Centric Teleop", group = "Examples")
public class ExampleTeleop_FieldCentric extends OpMode {
    private Follower follower;
    private ClawSubsystem claw;
    private final Pose startPose = new Pose(0,0,0);

    /** This method is call once when init is played, it initializes the follower and subsystems **/
    @Override
    public void init() {
        follower = new Follower(hardwareMap);
        follower.setStartingPose(startPose);

        claw = new ClawSubsystem(hardwareMap);
    }

    /** This method is called continuously after Init while waiting to be started. **/
    @Override
    public void init_loop() {
    }

    /** This method is called once at the start of the OpMode. **/
    @Override
    public void start() {
    }

    /** This is the main loop of the opmode and runs continuously after play **/
    @Override
    public void loop() {

        /** Movement Sector **/
        follower.setTeleOpMovementVectors(-gamepad1.left_stick_y, -gamepad1.left_stick_x, -gamepad1.right_stick_x, false);
        follower.update();

        /** Claw Sector **/
        if (gamepad1.left_bumper) {
            claw.closeLClaw();
        } else {
            claw.openLClaw();
        }

        if (gamepad1.right_bumper) {
            claw.closeRClaw();
        } else {
            claw.openRClaw();
        }

        /** This could be paired with a PIDF to set the target position of the lift in teleop.
         * For this, you would have to update the lift pid and make sure to initializes the lift subsystem.
         **/

        /*
        if (gamepad1.left_trigger > 0.5) {
            lift.setTarget(lTarget-50);
        }

        if (gamepad1.right_trigger > 0.5) {
            lift.setTarget(lTarget+50);
        }
        */


        telemetry.addData("X", follower.getPose().getX());
        telemetry.addData("Y", follower.getPose().getY());
        telemetry.addData("Heading in Degrees", Math.toDegrees(follower.getPose().getHeading()));
    }

    /** We do not use this because everything automatically should disable **/
    @Override
    public void stop() {
    }
}