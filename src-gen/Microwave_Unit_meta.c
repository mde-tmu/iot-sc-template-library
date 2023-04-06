/** Generated by itemis CREATE code generator. */

#include "Microwave_Unit_meta.h"
		
sc_string Microwave_Unit_meta_feature_names[] = {
	"<nothing>",
	"message",
	"food_inside_sensed",
	"in_use",
	"device.on",
	"device.off",
	"device.isOn",
	"device.start",
	"device.pause",
	"device.addTimer",
	"device.resetTimer",
	"device.timer",
	"Door.open",
	"Door.close",
	"Door.closed"
};

sc_string Microwave_Unit_meta_state_names[] = {
	"<nostate>",
	"_Microwave_Unit_._off_",
	"_Microwave_Unit_._on_",
	"_Microwave_Unit_._on_._Microwave_isWorking_.idle_standby",
	"_Microwave_Unit_._on_._Microwave_isWorking_.startTimer",
	"_Microwave_Unit_._on_._Microwave_isWorking_.startTimer._Starting_Timer_.pause",
	"_Microwave_Unit_._on_._Microwave_isWorking_.startTimer._Starting_Timer_.start",
	"_Microwave_Unit_._on_._Microwave_isWorking_.addTimer",
	"_Microwave_Unit_._on_.doorStatus.DoorClosed",
	"_Microwave_Unit_._on_.doorStatus.DoorOpened"
};
