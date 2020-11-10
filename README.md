# MEET APP - Empowerment of tumor patients during their treatment

Meet app is the Final Degree Project I made for the Computer Engineering Degree by the University of Santiago de Compostela.

The idea was given by the company I was working for by that time [Bahia Software], as the Galician Public Health System was demanding an application to empower patients. The company gave this idea so I could make an initial approach, set the basis and make a first iteration that they could pick up and continue working with.

In this project, all the learning of new languages and tools (Android Studio and Kotlin) was done independently as we don’t receive any formation about Android in the Degree.

All the documentation and sample data from the application is in [Galician language](https://en.wikipedia.org/wiki/Galician_language), as the project was done on the Galician University System, aimed towards the Galician Public Health System, as mentioned.

The final result was evaluated with a 9’3.

## Table of contents

- [Description of the project’s draft](#description-of-the-projects-draft)
  - [Introduction](#introduction)
  - [Objectives](#objectives)
  - [Technical description](#technical-description)
  - [Necessary material resources](#necessary-material-resources)
  - [Work phases and estimation](#work-phases-and-estimation)

## Description of the project's draft

### Introduction

In cancer detection, the speed of the answer of the medical process for the patient's survival is crucial. The process begins with a doctor's suspicion that the patient may have this disease. A series of tests are made to the patient, and in case of being positives, an expert committee gathers to dictate a treatment. At each moment of the process there will be an expert assigned to the patient who will be in charge of his follow-up.

In many cases, due to the fact that patients forget their appointments, or are not prepared for them (they are not fasted), the processes that lead to the detection and treatment of tumors are delayed with respect to the established guidelines. Complicating their effectiveness.

Currently, a trend of patient empowerment is being followed. Since the patient, being the centerpiece, it is vital that they have as much useful information as possible about the disease and the process that is taking place.

Patient empowerment consists of giving the patient the ability to decide and have control over their life and their treatment. This is the opposite of the previous system, in which the experts took care of everything, without taking the patient into account when making decisions.

In the past, a doctor did not give all the information to a patient, also trying to soften the vision of the patient towards his illness. The current trend is the opposite. It is to give all the information that may be useful for the patient. Either about his illness, about his current state, about what is the process that he followed and will continue for his recovery ... All this through a constant connection of information between the expert in charge of the patient and him.

### Objectives

The main objective of this project is the creation of a native mobile application of the Android Operating System, to be able to carry out the empowerment of the patient in a simple and satisfactory way. That is to say, it will be sought for the patient to see that the feedback they leave in the application, the information they receive (both notices and medical information), give the patient a totally central role within their ailment. For this, it will be necessary to meet the following sub-objectives:

1. The patient will be able to view his file at any time, accessing this information from his mobile terminal, through the developed application.
2. The application must be a channel for the exchange of information between the patient and the professional assigned to him. For this, the experts will be able to share information at all times with the patient about their health, or general information about the disease, which they see as relevant to the patient. There will also be a chat to send messages between the patient and the expert.
3. The application will show notifications and alerts to the patient that will arrive from a centralized system and already developed within the collaborating company.
4. The application will ask the patient to record feedback on the health status and monitor the process throughout its duration.

### Technical description

This application will communicate the patient/caregivers, with the professionals in charge of them from the health system, for the exchange of information during the process of treating the patient. The application will work simultaneously with a system for managing tumor committees, which will be in charge of generating the application notifications. The application will then be a connecting bridge between the system and the application users, being patients or experts.

![App interaction](https://github.com/AlejandroFraga/meet-app/blob/main/images/interaction.png?raw=true)

The analysis phase will focus on understanding how the patient will be able to visualize their record, with summary information focused on it. In this way, the patient could access a subset of the information related to their health problems, ensuring that he has all the necessary information.
The information of the patient to be shown will be studied, the information that can be sent by the experts, to look for the most convenient and simple way to represent and send it. It will also review the current operation of the system to which the application will be incorporated so that it is consistent and works properly with the communication interface defined between them. The information to be displayed from patients will be as follows:
 * Citations.
 * Preparations required for diagnostic tests.
 * Guidelines for treatments.
 * Information about their illness.

The different notifications will be studied, which will be focused on showing:
 * Citation: date, time, place, necessary preparations…
 * Indications for treatment.

We will study how to conduct chat communications between patients and experts. It will look for how to get useful and simple feedback for experts, through concise and currently adopted questions when observing the evolution of a patient with the same characteristics.

In the design phase, as we can see in the simple diagram of the situation of the application in its environment, the application will work in conjunction with the system for management of tumor committees. Which will not be part of this project and is already in operation. This system will have a communications interface already defined with the application, both for notifications and for the exchange of information of each of the patients or professionals.

We will find that the application is attractive for use by patients. With a simple, intuitive and visual functionality and graphic design. For this we will use gamification techniques. These have been shown to be very useful in increasing the motivation and commitment of patients in the care of their health.

We will design how the patient information will be displayed. Also the operation of the chat, which will be controlled by the application, which will be responsible for transmitting and receiving messages from patients to experts, and vice versa.

The application will be created using the official IDE for Android: Android Studio.

The programming languages will be used:
 * Java: for a certain part of the application’s functionalities.
 * Kotlin: for most part of the application’s functionalities. Being an official open source Android language with full interoperability with Java.

### Necessary material resources
 * Personal computer
 * Android mobile device
 * Android Studio (official IDE for Android)
 * Microsoft Office Word 2016
 * Google Slides

### Work phases and estimation

A Final Degree Project will be 401,25 hours of autonomous work from the student and 11,25 hours of pressential work (tutorships and evaluation).
Planned weekly dedication (in hours/week): 27,5

|Phase|Time estimation (in weeks)|
|---|---|
|Project management|1|
|Initialization|3,5|
|Design|3,5|
|Development|6|
|Testing|2|
|Closing|1|

(For the management of the scope of the project, a WBS should be included in all cases as it’s on the PMBOK of the PMI. Chapter 5, Project Scope Management)

![Work Breakdown Structure](https://github.com/AlejandroFraga/meet-app/blob/main/images/wbs.png?raw=true)


[//]: # (Links)

[Bahia Software]: <https://bahiasoftware.es/home>
