package com.catalyst.interviews.berlinclock;

import java.time.LocalTime;

public class BerlinTimeClock implements TimeConverter {

	private static final String YELLOW_LIGHT = "Y";
	private static final String RED_LIGHT = "R";
	private static final String NO_LIGHT = "O";
	private static final String EMPTY_ROW_LENGTH_4 = "OOOO";
	private static final String NEW_LINE = "\n";
	
	private static final int TOP_ROW_LIGHT_VALUE = 5;


	@Override
	public String convertTime(String timeToConvert) {
		String berlinClockTime;
		boolean invalidHour = false;
		if (timeToConvert.startsWith("24")) {
			timeToConvert = timeToConvert.replaceFirst("24", "00");
			invalidHour = true;
		}
		LocalTime time = LocalTime.parse(timeToConvert);
		berlinClockTime = convertSeconds(time.getSecond());
		berlinClockTime += NEW_LINE;
		berlinClockTime += invalidHour ? convertHours(24) : convertHours(time.getHour());
		berlinClockTime += NEW_LINE;
		berlinClockTime += convertMinutes(time.getMinute());
		return berlinClockTime;
	}

	private String convertHours(int hour) {
		String firstRow = EMPTY_ROW_LENGTH_4;
		for (int i = TOP_ROW_LIGHT_VALUE; i < 24; i += TOP_ROW_LIGHT_VALUE) {
			if (i < hour) {
				firstRow = firstRow.replaceFirst(NO_LIGHT, RED_LIGHT);
			}
		}
		return firstRow + NEW_LINE + createSecondRow(hour, RED_LIGHT);
	}

	private String createSecondRow(int targetNumber, String lightColor) {
		String secondRow = EMPTY_ROW_LENGTH_4;
		int numberOfLightsOnSecondRow = targetNumber % TOP_ROW_LIGHT_VALUE;
		for (int i = 0; i < numberOfLightsOnSecondRow; i++) {
			secondRow = secondRow.replaceFirst(NO_LIGHT, lightColor);
		}
		return secondRow;
	}

	private String convertMinutes(int targetMinute) {
		String firstRow = "";
		int currentMinute = TOP_ROW_LIGHT_VALUE;
		while (currentMinute < targetMinute) {
			if (currentMinute % 15 == 0) {
				firstRow += RED_LIGHT;
			} else {
				firstRow += YELLOW_LIGHT;
			}
			currentMinute += TOP_ROW_LIGHT_VALUE;
		}
		while (currentMinute < 60) {
			firstRow += NO_LIGHT;
			currentMinute += TOP_ROW_LIGHT_VALUE;
		}

		return firstRow + NEW_LINE + createSecondRow(targetMinute, YELLOW_LIGHT);
	}

	private String convertSeconds(int second) {
		if (second % 2 == 0) {
			return YELLOW_LIGHT;
		} else {
			return NO_LIGHT;
		}
	}
}