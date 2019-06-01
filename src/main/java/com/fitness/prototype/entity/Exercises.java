package com.fitness.prototype.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name="exercises")
public class Exercises {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column(name="workout_day")
	private String workoutDay;
	
	@NotBlank(message = "cannot leave name blank")
	@Size(max=85)
	@Column(name="exercise_name")
	private String exerciseName;
	
	@Column(name="primary_muscle_worked")
	private String primaryMuscleWorked;
	
	@Column(name="secondary_muscle_worked")
	private String secondaryMuscleWorked;
	
	@Column(name="equipment_required")
	private String equipmentRequired;
	
	
	@ManyToOne
	@JoinColumn(name="client_id")
	private Client creator;
		
	public Exercises() {
		
	}
	
	public Exercises(String exercisName, String primaryMuscleWorked,
			String secondaryMuscleWorked, String equipmentRequired) {
		this.exerciseName = exercisName;
		this.primaryMuscleWorked = primaryMuscleWorked;
		this.secondaryMuscleWorked = secondaryMuscleWorked;
		this.equipmentRequired = equipmentRequired;
	}
	
	public String getWorkoutDay() {
		return workoutDay;
	}

	public void setWorkoutDay(String workoutDay) {
		this.workoutDay = workoutDay;
	}

	public String getExerciseName() {
		return exerciseName;
	}

	public void setExerciseName(String exerciseName) {
		this.exerciseName = exerciseName;
	}

	public String getPrimaryMuscleWorked() {
		return primaryMuscleWorked;
	}
		
	public String getSecondaryMuscleWorked() {
		return secondaryMuscleWorked;
	}

	public void setSecondaryMuscleWorked(String secondaryMuscleWorked) {
		this.secondaryMuscleWorked = secondaryMuscleWorked;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setPrimaryMuscleWorked(String primaryMuscleWorked) {
		this.primaryMuscleWorked = primaryMuscleWorked;
	}

	public String getEquipmentRequired() {
		return equipmentRequired;
	}

	public void setEquipmentRequired(String equipmentRequired) {
		this.equipmentRequired = equipmentRequired;
	}

	public Client getCreator() {
		return creator;
	}

	public void setCreator(Client creator) {
		this.creator = creator;
	}

	@Override
	public String toString() {
		return "Exercises [id=" + id + ", exercisName=" + exerciseName + ", primaryMuscleWorked=" + primaryMuscleWorked
				+ ", secondaryMuscleWorked=" + secondaryMuscleWorked + ", equipmentRequired=" + equipmentRequired + "]";
	}



	
	
	

}
