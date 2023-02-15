

####open file 

open_file = open("student_info.txt" , "r")


####seperate the information 

condition = True

##variable 

student_name = ""
student_id = ""
student_major = ""
count = 0
infoList = []
infoDict = {}


while condition != False :
    count = count + 1 
    full_line = open_file.readline()
    course_code = []
    course_name = []
    course_credit = []
    course_grade = []
    if full_line != "" :
        
    #read each line and seperate the courses info from student info
    
        courses = full_line.rsplit(";")
        student_info = courses[0].rsplit(",")
        courses.pop(0)
        
    #add seperated info in seperated list 
    
        student_name = student_info[0]
        student_id = student_info[1]
        student_major = student_info[2]
        
    #seperate each course 
    
        for element in range(0,len(courses)) :
            c_info = courses[element].rsplit(",")
            course_code.append(c_info[0])
            course_name.append(c_info[1])
            course_credit.append(c_info[2])
            course_grade.append(c_info[3])
        course_grade[-1] = course_grade[-1].replace("\n","")
            
    #add each info to its appropriatt dict    
    
     #make final Dict
    
        infoDict = {}
        infoDict["Student name"] = student_name
        infoDict["Student id"] = student_id
        infoDict["Student major"] = student_major
        infoDict["Course code"] = course_code
        infoDict["Course name"] = course_name
        infoDict["Course credit"] = course_credit
        infoDict["Course grade"] = course_grade
        infoList.append(infoDict)
    
    else : 
        condition = False 
        
        
        
####display options 

## We will call each function here. 

def main(): 
    final_list = []
    end = False 
    while end != True : 
        ## show main menu:
        print("1-Display registered courses for a student and his GPA.\n2-Display students in a course.\n3-Display students with GPA more than or equal to an entered GPA value.\n4-Add a course to a student.\n5-Drop a course from a student.\n6-Add student info.\n7-Delete student info.\n8-Save data to the file.\n9-Quit.")
        ## make sure the input is valid . 
        option = int(input("Choose an option : "))
        if option != 1 and option != 2 and option != 3 and option != 4 and option != 5 and option != 6 and option != 7 and option != 8 and option != 9 :
            print("Wrong choice . Please enter between 1 - 9")
            
        ## option 1 
        elif option == 1 : 
            try :
                ID = input("Enter student id : ")
                if ID.startswith("2") == False or ID.endswith("0") == False or len(ID) != 9 :
                    print("Invalid ID")
                else :
                    print(display_course_GPA(ID,infoList))
            except ZeroDivisionError : 
                print("There is no course yet!")
                
        ## option 2 
        elif option == 2 :
            course_code = input("Enter The code for the course : ")
            if len(display_studens_in_course(course_code,infoList)) != 0 :
                displayStudent = display_studens_in_course(course_code,infoList)
                print("The students in this course are :" )
                for i in range(len(displayStudent)):
                    print(displayStudent[i])
            else : 
                print("There are no student in this course.")
                
        ## option 3 
        elif option == 3 : 
            newGPA = float(input("Enter GPA : "))
            if newGPA < 0 or newGPA > 4 : 
                print("Invalid GPA enter GPA between 0 - 4")
            else :
                print("The student more than or equal {} are :".format(newGPA))
                displayGPA = display_student_moreGPA(newGPA,infoList)
                for i in range(len(displayGPA)):
                    print(displayGPA[i])
                
        ## option 4 
        elif option == 4 : 
            ID = input("Enter ID : ")
            if ID.startswith("2") == False or ID.endswith("0") == False or len(ID) != 9 :
                print("Invalid ID")
            else : 
                print(add_course(ID,infoList)[0])
                
                
        ## option 5 
        elif option == 5 :
            ID = input("Enter ID : ")
            if ID.startswith("2") == False or ID.endswith("0") == False or len(ID) != 9 :
                print("Invalid ID")
            else : 
                print(drop_course(ID,infoList)[0])
                
        ## option 6
        elif option == 6 : 
            ID = input("Enter ID : ")
            if ID.startswith("2") == False or ID.endswith("0") == False or len(ID) != 9 :
                print("Invalid ID")
            else : 
                added_student = add_student_info(ID,infoList)
                print(added_student[0])
                
        ## option 7
        elif option == 7 :
            ID = input("Enter ID : ")
            if ID.startswith("2") == False or ID.endswith("0") == False or len(ID) != 9 :
                print("Invalid ID")
            else : 
                print(remove_student_info(ID,infoList)[0])
                
        ## option 8
        elif option == 8 :
            save(infoList)
            print("File saved successfully!")
        ## option 9 
        elif option == 9 :
            # closing file finaly!
            open_file.close()
            print("\n GoodBye\n\n ^     ^\n    |\n\n(_______)")
            break 
                
        input("Press enter to precede ")
            

    
####option 1 

def display_course_GPA(ID,infoList):
        total_credits = 0
        total_points = 0 
        findCourses = []
        for element in range(len(infoList)) : 
            if infoList[element]["Student id"] == ID :
                findCourses = (infoList[element]["Course name"])
                for i in range(len(infoList[element]["Course credit"])) : 
                    total_credits += int(infoList[element]["Course credit"][i])
                    total_points += int(infoList[element]["Course credit"][i])*letterCalc(infoList[element]["Course grade"][i])
                GPA = total_points / total_credits
                return ("GPA is {} and courses :{}\n".format(round(GPA,2) , findCourses))
                #Just print the format is ready!
        else : 
            return "ID number doesn't exist!"
            #Just print the format is ready!
                
####option 2

def display_studens_in_course(course_code,infoList):
    course_list = []
    for element in range(len(infoList)):
        for i in range(len(infoList[element]["Course code"])):
            if infoList[element]["Course code"][i] == course_code.upper() : 
                course_list.append((infoList[element]["Student name"],infoList[element]["Student id"]))
    return course_list
        #(Name , ID)
    
####option 3

def display_student_moreGPA(newGPA,infoList):
    GPA_list = []
    for element in range(len(infoList)):
        total_credits = 0
        total_points = 0 
        for i in range(len(infoList[element]["Course credit"])) : 
                total_credits += int(infoList[element]["Course credit"][i])
                total_points += int(infoList[element]["Course credit"][i])*letterCalc(infoList[element]["Course grade"][i])
        GPA = total_points / total_credits
        if GPA >= newGPA :
            GPA_list.append((infoList[element]["Student name"],infoList[element]["Student id"]))
    return GPA_list 
        #(Name , ID)
            
#option 4

def add_course(ID,infoList): 
    for element in range(len(infoList)):
        if infoList[element]["Student id"] == ID : 
            course_name = input("Enter the new course name ")
            for i in infoList[element]["Course name"]:
                if course_name.upper() == i.upper() :
                    return "Course are already exist!",0
            course_code = input("Enter the new course code ")
            for i in infoList[element]["Course code"]:
                if course_code.upper() == i.upper() :
                    return "Course code are already exist!",0
            credit_hour = input("Enter the new credit hour ")
            grade = input("Enter the new course grade ")
            infoList[element]["Course name"].append(course_name)
            infoList[element]["Course code"].append(course_code.upper())
            infoList[element]["Course credit"].append(credit_hour)
            infoList[element]["Course grade"].append(grade)
            return ("Course added successfully" , infoList)
    return "ID number doesn't exist!",0
    #We put two argument to substitute infoList
    #Just print the format is ready!
            
####option 5
def drop_course(ID,infoList):
    for element in range(len(infoList)):
        if infoList[element]["Student id"]==ID :
            course_code = input("Enter the course code to be dropped : ")
            for i in range(len(infoList[element]["Course code"])):
                if course_code.upper() == infoList[element]["Course code"][i]:
                    infoList[element]["Course code"].remove(course_code.upper())
                    infoList[element]["Course name"].pop(i)
                    infoList[element]["Course credit"].pop(i)
                    infoList[element]["Course grade"].pop(i)
                    return "Course removed successfully!",infoList
            return"Course doesn't exist!",0
    return "ID number doesn't exist!",0
    #We put two argument to substitute infoList
    #Just print the format is ready!
    
####option 6
def add_student_info(ID,infoList):
    for element in range(len(infoList)):
        if infoList[element]["Student id"] == ID :
            return "ID number is already exist!",0
            #We put two argument to substitute infoList
            #Just print the format is ready!
    stud_name = input("Enter the name : ")
    stud_major = input("Enter the major : ")
    return "Student information has added successfully!" , infoList.append({"Student name":stud_name , "Student id":ID , "Student major":stud_major , "Course code" :[] , "Course name":[],"Course credit":[],"Course grade":[]})
    #Just print the format is ready!
    
####option 7
def remove_student_info(ID,infoList):
    for element in range(len(infoList)):
        if infoList[element]["Student id"] == ID :
            return "Student information removed successfully!" , infoList.pop(element)
    return "ID number doesn't exist!",0
    #We put two argument to substitute infoList
    #Just print the format is ready!
            
####option 8
def save(final_list):
    final_txt = ""
    ## open the same file to save all changes .
    saved_file = open("student_info.txt" , "w")
    for element in range(len(final_list)):
        final_course_txt = ""
        for i in range(len(final_list[element]["Course code"])):
            final_course_txt += ";"+ final_list[element]["Course code"][i]+","+final_list[element]["Course name"][i]+","+final_list[element]["Course credit"][i]+","+final_list[element]["Course grade"][i]
        final_txt += final_list[element]["Student name"]+","+final_list[element]["Student id"]+","+final_list[element]["Student major"]+final_course_txt+"\n"
    final_txt = final_txt[:-1]
    return saved_file.write(final_txt)

    
    
####option 9 --> is in main function.



## ----- Other function 

####Grade calculation function

def letterCalc(letter):
    if "\n" in letter :
        letter = letter[:-1]
    if letter == "A+" :
        return 4
    elif letter == "A" : 
        return 3.75
    elif letter == "B+" :
        return 3.50
    elif letter == "B" : 
        return 3
    elif letter == "C+" : 
        return 2.50 
    elif letter == "C" :
        return 2 
    elif letter == 'D+' : 
        return 1.50 
    elif letter == "D" : 
        return 1 
    elif letter == "F" :
        return 0

    
### Finaly recall the main function!
main()