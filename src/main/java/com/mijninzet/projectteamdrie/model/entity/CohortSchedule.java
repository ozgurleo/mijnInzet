package com.mijninzet.projectteamdrie.model.entity;

import com.mijninzet.projectteamdrie.model.entity.user.User;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Entity
public class CohortSchedule {
   @Id
   private int id;
   private LocalDate startWeekDay;
   private LocalDate endWeekDay;
   private String day;
   private String daypart;

   @ManyToOne
   User user;

   @ManyToOne
   Subject subject;

   private String classRoom;

   @ManyToOne
   Cohort cohort;

   public CohortSchedule() {
      super();
   }

   public CohortSchedule(LocalDate startWeekDay, LocalDate endWeekDay, String day, String daypart, User user, Subject subject, String classRoom, Cohort cohort) {
      this.startWeekDay = startWeekDay;
      this.endWeekDay = endWeekDay;
      this.day = day;
      this.daypart = daypart;
      this.user = user;
      this.subject = subject;
      this.classRoom = classRoom;
      this.cohort = cohort;
   }

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public LocalDate getStartWeekDay() {
      return startWeekDay;
   }

   public void setStartWeekDay(LocalDate startWeekDay) {
      this.startWeekDay = startWeekDay;
   }

   public LocalDate getEndWeekDay() {
      return endWeekDay;
   }

   public void setEndWeekDay(LocalDate endWeekDay) {
      this.endWeekDay = endWeekDay;
   }

   public String getDay() {
      return day;
   }

   public void setDay(String day) {
      this.day = day;
   }

   public String getDaypart() {
      return daypart;
   }

   public void setDaypart(String daypart) {
      this.daypart = daypart;
   }

   public User getUser() {
      return user;
   }

   public void setUser(User user) {
      this.user = user;
   }

   public Subject getSubject() {
      return subject;
   }

   public void setSubject(Subject subject) {
      this.subject = subject;
   }

   public String getClassRoom() {
      return classRoom;
   }

   public void setClassRoom(String classRoom) {
      this.classRoom = classRoom;
   }

   public Cohort getCohort() {
      return cohort;
   }

   public void setCohort(Cohort cohort) {
      this.cohort = cohort;
   }
}
