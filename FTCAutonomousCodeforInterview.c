\\#pragma config(Hubs,  S1, HTMotor,  HTMotor,  HTServo,  none)
#pragma config(Sensor, S1,     ,               sensorI2CMuxController)
#pragma config(Sensor, S2,     HTIRS2,         sensorI2CCustom)
#pragma config(Motor,  mtr_S1_C1_1,     motorD,        tmotorTetrix, openLoop)
#pragma config(Motor,  mtr_S1_C1_2,     motorE,        tmotorTetrix, openLoop)
#pragma config(Motor,  mtr_S1_C2_1,     motorF,        tmotorTetrix, openLoop)
#pragma config(Motor,  mtr_S1_C2_2,     motorG,        tmotorTetrix, openLoop)
#pragma config(Servo,  srvo_S1_C3_1,    servo1,               tServoStandard)
#pragma config(Servo,  srvo_S1_C3_2,    servo2,               tServoStandard)
#pragma config(Servo,  srvo_S1_C3_3,    servo3,               tServoNone)
#pragma config(Servo,  srvo_S1_C3_4,    servo4,               tServoNone)
#pragma config(Servo,  srvo_S1_C3_5,    servo5,               tServoNone)
#pragma config(Servo,  srvo_S1_C3_6,    servo6,               tServoNone)
//*!!Code automatically generated by 'ROBOTC' configuration wizard               !!*//

//#pragma config(Sensor, S2,     IR,             sensorHiTechnicIRSeeker1200)

//*!!Code automatically generated by 'ROBOTC' configuration wizard               !!*//

/////////////////////////////////////////////////////////////////////////////////////////////////////
//
//                           Autonomous Mode Code Template
//
// This file contains a template for simplified creation of an autonomous program for an TETRIX robot
// competition.
//
// You need to customize two functions with code unique to your specific robot.
//
/////////////////////////////////////////////////////////////////////////////////////////////////////

#include "JoystickDriver.c"  //Include file to "handle" the Bluetooth messages.
#include "drivers/hitechnic-irseeker-v2.h"


/////////////////////////////////////////////////////////////////////////////////////////////////////
//
//                                    initializeRobot
//
// Prior to the start of autonomous mode, you may want to perform some initialization on your robot.
// Things that might be performed during initialization include:
//   1. Move motors and servos to a preset position.
//   2. Some sensor types take a short while to reach stable values during which time it is best that
//      robot is not moving. For example, gyro sensor needs a few seconds to obtain the background
//      "bias" value.
//
// In many cases, you may not have to add any code to this function and it will remain "empty".
//
/////////////////////////////////////////////////////////////////////////////////////////////////////


void initializeRobot();
void go(float dist,float rpm);
void turn(float deg, float dir, float rpm);
void dropBlock();
int BeaconCheck();


/////////////////////////////////////////////////////////////////////////////////////////////////////
//
//                                         Main Task
//
// The following is the main code for the autonomous robot operation. Customize as appropriate for
// your specific robot.
//
// The types of things you might do during the autonomous phase (for the 2008-9 FTC competition)
// are:
//
//   1. Have the robot follow a line on the game field until it reaches one of the puck storage
//      areas.
//   2. Load pucks into the robot from the storage bin
//   3. Stop the robot and wait for autonomous phase to end.
//
// This simple template does nothing except play a periodic tone every few seconds.
//
// At the end of the autonomous period, the FMS will autonmatically abort (stop) execution of the program.
//
/////////////////////////////////////////////////////////////////////////////////////////////////////

task main()
{

	initializeRobot();


	go(27, 100);
	motor[motorD]=-100;
	  motor[motorE]=-100;
	  wait1Msec(300);

		int i=0;

		while(i<=14) //while time hasn't expired
		{

			i=i+1;

			go(6,153);

			if (BeaconCheck() == 1)
			{
			  go(9,75);
			//go an adjustment dist
				dropBlock();

						return;
			}
		}
	 dropBlock();


}




void initializeRobot()
{


  waitForStart();

  return;
}
void go(float dist,float rpm)
{
	 // part one of the normalizing rpm
	 float nrpm = (rpm / 154)* 100;
	 float circum = 3.14159 * 4;
   float revs = dist / circum;
   float spr = 60 / rpm;
   float time = spr * revs * 1000;
   motor[motorD]=nrpm;
   motor[motorE]=-nrpm;
   wait1Msec(time);
   motor[motorD]=0;
   motor[motorE]=0;
   return;
}
void turn(float deg, float dir, float rpm)
{
	 float nrpm = (rpm / 154)* 100;

	 float percent_rot = deg / 360;

	 float wheel_rad = 2;
	 float wheel_circum = 2 * 3.14159 * wheel_rad;

	 float robot_rad = 8;
	 float robot_circum = 2 * 3.14159 * robot_rad ;

   float revs =   percent_rot * (robot_circum / wheel_circum);
   //how many 4 inches = percent_rot of ~23 inches
   //how many wheel circumferences (4in) equal percent_rot percent of the vehicle cicumference(16)?
   float spr = 60 / rpm;
   float time = spr * revs * 1000;
   if (dir == 0)
   {
   		//turn left
  	  motor[motorD]=nrpm;
   		motor[motorE]=-nrpm;
   }
   if (dir == 1)
   {
   		//turn right
   		motor[motorD]=-nrpm;
   		motor[motorE]=nrpm;
   }
   //motor[motorD]=nrpm;
   //motor[motorE]=-nrpm;
   wait1Msec(time);
   motor[motorD]=0;
   motor[motorE]=0;
}

int BeaconCheck()
{
 	//variable init
	int dirAC, acS1, acS2, acS3, acS4, acS5 = 0;

	//set sensor parameters
	tHTIRS2DSPMode _mode = DSP_1200;
	HTIRS2setDSPMode(HTIRS2, _mode);

	dirAC = HTIRS2readACDir(HTIRS2);																	//get raw data
	HTIRS2readAllACStrength(HTIRS2, acS1, acS2, acS3, acS4, acS5);		//get raw data

	nxtDisplayTextLine(0, "dirAC: %d",dirAC);
	nxtDisplayTextLine(1, "acS1: %d", acS1);
	nxtDisplayTextLine(2, "acS2: %d", acS2);
	nxtDisplayTextLine(3, "acS3: %d", acS3);
	nxtDisplayTextLine(4, "acS4: %d", acS4);
	nxtDisplayTextLine(5, "acS5: %d", acS5);


	wait1Msec(50);                    																// wait 50 milliseconds
	if ((acS3 >= 20) && (acS2 <= 10) && (acS4 <= 10))
	{
		nxtDisplayTextLine(6, "True");  // display the string, 's1' on line 3
		return 1;
	}
	else
	{
		nxtDisplayTextLine(6, "False");  // display the string, 's1' on line 3
		return 0;
	}
	servo[servo1] = 180;
	wait1Msec(1500);
	servo[servo1] = 0;
}

void dropBlock()
{
	servo[servo1] = 0;
	wait1Msec(3000);
	servo[servo1] = 230;
	wait1Msec(3000);
}
