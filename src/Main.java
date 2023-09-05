import java.lang.reflect.*;

import java.util.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

	static List<String> classes =  new ArrayList<String>();

	//	public Main() {
	//		classes = new ArrayList<String>();
	//
	//	}

	public static boolean checkIfPackage(String fileName){

		for(int i=0;i<fileName.length();i++) {
			if(fileName.charAt(i)=='.') {
				return false;
			}
		}

		return true;
	}

	public static void getClasses() {

		File directory = new File("src/");
		// store all names with same name
		String[] flist = directory.list();

		// Linear search in the source
		for (int i = 0; i < flist.length; i++) {

			String filename = flist[i];

			if(!checkIfPackage(filename)) { //   class
				classes.add(filename.substring(0, filename.length()-5));
			}
			else { // Linear search in package 
				File newDirectory = new File("src/"+filename);
				String[] plist = newDirectory.list();
				for (int j = 0; j < plist.length; j++) {
					String className = plist[j];

					className = className.substring(0, className.length()-5);//remove extension

					classes.add(filename+"."+className);

				}

			}
		}
	}

	public static void main(String[] args) {

		getClasses();
		for (String className : classes) {

			try {

				Class<?> clazz = Class.forName(className);


				// CSV
				String csvFilePath = clazz.getName() +".csv";
				FileWriter fileWriter = new FileWriter(csvFilePath);


				//print name
				System.out.println(clazz.getSimpleName());
				if(clazz.isAnnotation()) {
					fileWriter.write("Annotation Name");
				}
				else if(clazz.isInterface()) {
					fileWriter.write("Interface Name");

				}
				else if(clazz.isEnum()){
					fileWriter.write("Enum Name");
				}
				else {
					fileWriter.write("Class Name");
				}
				fileWriter.write(",");
				fileWriter.write(clazz.getSimpleName());
				fileWriter.write(",");
				fileWriter.write("\n");

				//print package
				System.out.println(clazz.getPackageName());
				fileWriter.write("Package Name");
				fileWriter.write(",");
				fileWriter.write(clazz.getPackageName());
				fileWriter.write(",");
				fileWriter.write("\n");


				//print Super
				try {
					System.out.println(clazz.getSuperclass().getSimpleName());
					fileWriter.write("Super");
					fileWriter.write(",");
					fileWriter.write(clazz.getSuperclass().getName());
					fileWriter.write(",");
					fileWriter.write("\n");
				} catch (Exception e) {
					System.out.println("interface");
				}

				//print implemented interfaces
				fileWriter.write("Interfaces");
				fileWriter.write(",");
				for (Class<?> myinterface : clazz.getInterfaces()) {
					System.out.println(myinterface.getName());
					fileWriter.write(myinterface.getName());
					fileWriter.write(",");

				}
				fileWriter.write("\n");


				//print field
				fileWriter.write("Fields");
				fileWriter.write(",");
				for (Field field : clazz.getDeclaredFields()) {
					System.out.println(field.toString());
					fileWriter.write(field.toString());
					fileWriter.write(",");

				}
				fileWriter.write("\n");


				//print methods
				fileWriter.write("Constructors");
				fileWriter.write(",");
				for (Constructor<?> constructor : clazz.getDeclaredConstructors()) {
					System.out.println(constructor);
					fileWriter.write(constructor.toString());
					fileWriter.write(",");
				}
				fileWriter.write("\n");

				//print methods
				fileWriter.write("Methods");
				fileWriter.write(",");
				for (Method method : clazz.getDeclaredMethods()) {
					System.out.println(method);
					fileWriter.write(method.toString());
					fileWriter.write(",");
				}

				System.out.println("-------");

				// Close the FileWriter object
				fileWriter.close();

			}catch (ClassNotFoundException e) {
				System.out.println("not java class");
				System.out.println("------------");
			}
			catch (Exception e) {
				System.out.println(e.getMessage());
			}

		}
	}

}
