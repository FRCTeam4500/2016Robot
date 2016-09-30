package org.usfirst.frc.team4500.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AimAndFire2 extends CommandGroup {

    public  AimAndFire2() {
    	addSequential(new AimCannon());
    }
}
