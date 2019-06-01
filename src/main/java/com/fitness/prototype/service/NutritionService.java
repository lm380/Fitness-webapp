package com.fitness.prototype.service;

import org.springframework.stereotype.Service;

import com.fitness.prototype.entity.Client;

@Service
public class NutritionService {

	public double calculateCalories(String gender, Integer age, Integer weight, Integer height, int activityLevel,
			String goal) {
		double bmr;
		double calories;
		if (gender.equals("male")) {
			bmr = 5.0 + (9.99 * weight.doubleValue()) + (6.25 * height.doubleValue()) - (4.92 * age.doubleValue());
		} else {
			bmr = (9.99 * weight.doubleValue()) + (6.25 * height.doubleValue()) - (4.92 * age.doubleValue()) - 161;
		}
		if (activityLevel == 1) {
			calories = bmr * 1.2;
		} else if (activityLevel == 2) {
			calories = bmr * 1.375;
		} else if (activityLevel == 3) {
			calories = bmr * 1.55;
		} else {
			calories = bmr * 1.725;
		}
		if (goal.equals("Gain")) {
			calories += 500;
		} else if (goal.equals("Cut")) {
			calories -= 500;
		} else {

		}
		return calories;
	}

	public Client setNutritionInfo(Client client, int calories) {
		int protein = (int) (client.getWeight() * 2.2 * 0.85);
		int carbs = (int) (((calories - (protein * 4.0)) / 2.0) / 4.0);
		int fat = (int) (((calories - (protein * 4.0)) / 2.0) / 9.0);
		client.setCalories(calories);
		client.setProtein(protein);
		client.setCarbs(carbs);
		client.setFat(fat);
		return client;

	}

}
