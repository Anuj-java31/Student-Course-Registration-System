import java.util.ArrayList;
import java.util.Scanner;

class Course {
    String code;
    String name;
    int capacity;
    String schedule;
    String discription;
    ArrayList<Student> registeredStudents;

    public Course(String code, String name, int capacity, String discription, String schedule) {
        this.code = code;
        this.name = name;
        this.capacity = capacity;
        this.discription = discription;
        this.schedule = schedule;
        registeredStudents = new ArrayList<>();
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public int capacity() {
        return capacity;
    }

    public String getDisciption() {
        return discription;
    }

    public String schedule() {
        return schedule;
    }

    public boolean hasAvailableSlots() {
        return registeredStudents.size() < capacity;
    }

    public void registerStudents(Student student) {
        if (hasAvailableSlots()) {
            registeredStudents.add(student);
            System.out.println("Student " + student.getName() + " is registered successfully");
        } else {
            System.out.println("No slots are available");
        }
    }

    public void removeStudent(Student student) {
        if (registeredStudents.contains(student)) {
            registeredStudents.remove(student);
            System.out.println("Student " + student.getName() + " is removed");
        } else {
            System.out.println("Student " + student.getName() + " is not registered yet for this course");
        }
    }

    public int getAvailableSlots() {
        return capacity - registeredStudents.size();
    }

    public void getRegisteredStudents() {
        for (int i = 0; i < registeredStudents.size(); i++) {
            System.out.println(registeredStudents.get(i).getName());
        }
    }

    @Override
    public String toString() {
        return "Course name- " + name + "\nCourse code- " + code + "\nCapacity- " + capacity + "\nDiscription- "
                + discription + "\nSchedule- " + schedule;
    }
}

class CourseDatabase {
    private ArrayList<Course> courses;

    public CourseDatabase() {
        courses = new ArrayList<>();
    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    public void getAvailableCourses() {
        for (int i = 0; i < courses.size(); i++) {
            System.out.println(courses.get(i));
        }
    }

    public Course findCourseByCode(String code) {
        for (Course course : courses) {
            if (course.getCode().equalsIgnoreCase(code)) {
                return course;
            }
        }
        return null;
    }

    public void displayAvailableCourses() {
        for (Course course : courses) {
            System.out.println(course);
        }
    }
}

class Student {
    String name;
    String id;
    ArrayList<Course> registeredCourses;

    public Student(String name, String id) {
        this.name = name;
        this.id = id;
        registeredCourses = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public void registerCourses(Course course) {
        if (!registeredCourses.contains(course)) {
            registeredCourses.add(course);
            System.out.println(name + " is registered for the course- \n" + course + " successfully");
        } else {
            System.out.println(name + " is already registered for the course-\n " + course);
        }
    }

    public void dropCourse(Course course) {
        if (registeredCourses.contains(course)) {
            registeredCourses.remove(course);
            System.out.println("Course- " + course + "has been removed");
        } else {
            System.out.println(name + " is not registered for the course-\n " + course);
        }
    }

    public void getRegisteredCourse() {
        for (int i = 0; i < registeredCourses.size(); i++) {
            System.out.println(registeredCourses.get(i));
        }
    }

    @Override
    public String toString() {
        return "Student ID: " + id + "\nName: " + name;
    }
}

class StudentDatabase {
    private ArrayList<Student> students;

    public StudentDatabase() {
        students = new ArrayList<>();
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public Student findStudentByID(String ID) {
        for (Student student : students) {
            if (student.getId().equalsIgnoreCase(ID)) {
                return student;
            }
        }
        return null;
    }
}

public class studentCourseRegistration {
    public static void main(String[] args) throws InterruptedException {
        Scanner sc = new Scanner(System.in);
        CourseDatabase courseDatabase = new CourseDatabase();
        courseDatabase.addCourse(new Course("EMF", "Engineering Mathematics 1", 10,
                "A course in which you learn the basics concepts of mathematics used in engineering",
                "MON-THU 10:00 AM - 11:30 AM "));
        courseDatabase.addCourse(new Course("EMS", "Engineering Mathematics 1", 8,
                "A course in which you learn the advanced concepts of mathematics used in engineering",
                "TUE-FRI 2:00 PM - 3:30 PM"));
        courseDatabase.addCourse(new Course("DSAJ", "Data Structer and Algorithims with Java", 5,
                "A course in which you learn about the data structer and algorithims using java from scratch",
                "SAT - SUN 7:00 PM - 9:00 PM"));

        StudentDatabase studentDatabase = new StudentDatabase();
        studentDatabase.addStudent(new Student("ANUJ SINGH", "031"));
        studentDatabase.addStudent(new Student("Harshit", "032"));

        while (true) {
            System.out.println("\n1. View Available Courses");
            System.out.println("2. Register for a Course");
            System.out.println("3. Drop a Course");
            System.out.println("4. Exit");
            System.out.println("Enter your choice: ");

            int choice = sc.nextInt();
            sc.nextLine();
            int b = 1;
            switch (choice) {
                case 1:
                    courseDatabase.displayAvailableCourses();
                    break;
                case 2:
                    System.out.println("Enter your student ID");
                    String id = sc.nextLine();
                    Student student = studentDatabase.findStudentByID(id);
                    if (student != null) {
                        System.out.println("Enter course code to register: ");
                        String code = sc.nextLine();
                        Course course = courseDatabase.findCourseByCode(code);
                        if (course != null) {
                            student.registerCourses(course);
                        } else {
                            System.out.println("Course not found  ");
                        }
                    } else {
                        System.out.println("Student not found ");
                    }
                    break;
                case 3:
                    System.out.println("Enter your student ID");
                    id = sc.nextLine();
                    student = studentDatabase.findStudentByID(id);
                    if (student != null) {
                        System.out.println("Enter course code to drop: ");
                        String code = sc.nextLine();
                        Course course = courseDatabase.findCourseByCode(code);
                        if (course != null) {
                            student.dropCourse(course);
                        } else {
                            System.out.println("Course not found  ");
                        }
                    } else {
                        System.out.println("Student not found ");
                    }
                    break;
                case 4:
                    System.out.println("Exting....");
                    b = 0;
                    Thread.sleep(2000);
                    System.out.println("Thank you...");
                    break;
                default:
                    System.out.println("Invalid choice");
            }
            if (b == 0) {
                break;
            }
        }
        sc.close();
    }
}
