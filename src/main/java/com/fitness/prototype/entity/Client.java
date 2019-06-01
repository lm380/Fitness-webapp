package com.fitness.prototype.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import org.springframework.format.annotation.NumberFormat;

import com.fitness.prototype.validation.UniqueEmail;
import com.fitness.prototype.validation.UniqueUsername;

@Entity
@Table(name = "client")
public class Client {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@ManyToMany(cascade = { CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.LAZY)
	@JoinTable(name = "client_exercisesUsed", joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "exercise_id"))
	private List<Exercises> exercisesUsed;

	@OneToMany(cascade = {CascadeType.ALL},fetch = FetchType.EAGER)
	private List<Exercises> exercises;

	@Column(name = "num_exercises_created")
	private int numOfExercisesCreated;

	@NotBlank(message = "must choose a sex")
	@Column(name = "gender")
	private String gender;

	@NotBlank(message = "first name field must have characters")
	@Column(name = "first_name")
	private String firstName;

	@NotBlank(message = "last name field cannot be empty")
	@Column(name = "last_name")
	private String lastName;

	@Column
	@NotBlank(message = "user name field cannot be empty")
	@UniqueUsername
	private String username;

	@NotBlank(message = "email field cannot be left empty")
	@Column(name = "email")
	@UniqueEmail
	private String email;

	@NotBlank(message = "password field cannot be left empty")
	@Column(name = "password")
	private String password;

	@Column(name = "gym_days")
	private int gymDays;

	@Column(name = "gym_goal")
	private String gymGoal;

	@Column(name = "target_muscle")
	private String targetMuscle;

	@NumberFormat
	@Column(name = "age")
	private Integer age;

	@Column(name = "target_calories")
	private int calories;

	@Column(name = "target_protein")
	private int protein;


	@Column(name = "target_fat")
	private int fat;

	@Column(name = "target_carbs")
	private int carbs;

	@NumberFormat
	@Column(name = "weight")
	private Integer weight;

	@NumberFormat
	@Column(name = "height")
	private Integer height;

	@Column(name = "activity_level")
	private int activityLevel;

	@Column(name = "nutrition_goal")
	private String nutritionGoal;

	@ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},
			fetch = FetchType.EAGER)
	@JoinTable(name = "client_role", joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles;

	public Client() {

	}

	public List<Exercises> getExercisesUsed() {
		return exercisesUsed;
	}

	public void setExercisesUsed(List<Exercises> exercisesUsed) {
		this.exercisesUsed = exercisesUsed;
	}

	public String getNutritionGoal() {
		return nutritionGoal;
	}

	public void setNutritionGoal(String nutritionGoal) {
		this.nutritionGoal = nutritionGoal;
	}

	public int getProtein() {
		return protein;
	}

	public void setProtein(int protein) {
		this.protein = protein;
	}

	public int getFat() {
		return fat;
	}

	public void setFat(int fat) {
		this.fat = fat;
	}

	public int getCarbs() {
		return carbs;
	}

	public void setCarbs(int carbs) {
		this.carbs = carbs;
	}

	public int getActivityLevel() {
		return activityLevel;
	}

	public void setActivityLevel(int activityLevel) {
		this.activityLevel = activityLevel;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getNumOfExercisesCreated() {
		return numOfExercisesCreated;
	}

	public void setNumOfExercisesCreated(int numOfExercisesCreated) {
		this.numOfExercisesCreated = numOfExercisesCreated;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public int getCalories() {
		return calories;
	}

	public void setCalories(int calories) {
		this.calories = calories;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Exercises> getExercises() {
		return exercises;
	}

	public void setExercises(List<Exercises> exercises) {
		this.exercises = exercises;
	}

	public void addCreatedExercise(Exercises tempExercise) {
		if (numOfExercisesCreated < 10) {
			if (exercises == null) {
				exercises = new ArrayList<Exercises>();
			}
			exercises.add(tempExercise);
			numOfExercisesCreated++;
			tempExercise.setCreator(this);
		} else {

		}
	}

	public void addExercise(Exercises tempExercise) {

		exercisesUsed.add(tempExercise);
	}

	public int getGymDays() {
		return gymDays;
	}

	public void setGymDays(int gymDays) {
		this.gymDays = gymDays;
	}

	public String getGymGoal() {
		return gymGoal;
	}

	public void setGymGoal(String gymGoal) {
		this.gymGoal = gymGoal;
	}

	public String getTargetMuscle() {
		return targetMuscle;
	}

	public void setTargetMuscle(String targetMuscle) {
		this.targetMuscle = targetMuscle;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Client [id=" + id + ", exercises=" + exercises + ", numOfExercisesCreated=" + numOfExercisesCreated
				+ ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", password=" + password
				+ ", gymDays=" + gymDays + ", Role=" + roles + ", gymGoal=" + gymGoal + ", targetMuscle=" + targetMuscle
				+ ", age=" + age + ", calories=" + calories + "]";
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String userName) {
		this.username = userName;
	}

}
