package logic.controller;

import logic.model.Activity;
import logic.model.ActivityVector;

public class FindActivityController {
	ActivityVector vector = ActivityVector.getActivityVector();
	
	public Activity findActivity (int n) {
		//dummy method for get one activity!
		return vector.getActivity().get(n);
	}
}
