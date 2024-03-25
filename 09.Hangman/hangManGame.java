/*
 * hangManGame.java
 * Alden Shin-Culhane
 * 09/26/2018
 * Career computing hangman assignment game
 * 
 */

import java.util.Scanner;
class hangManGame{
  // array of 15 jobs 
  static String[]jobs = {
    "Cyber Security Analyst","Database Administrator","Game Developer","IT Consultant","Server Programmer",
    "Web Developer","Network Administrator","Network Architect","Mobile Application Developer",
    "Machine Learning Engineer","Network Analyst","System Administrator","Quality Assurance Engineer",
    "Data Scientist","User Experience Designer"
  };
  
  // array of 6 hints for every job, one initial hint, and then one after every wrong entry (up to 5 wrong entries)
  static String[][]hints = {
    {"Demand in this profession is expected to be driven by the increasing efforts to fight cyber crime",
      "Earns an average annual wage of $128,373 CAD ",
      "Includes performing risk assessments and the testing of data processing systems",
      "Requirements include a bachelor’s degree in computer information systems, programming, or any related field",
      "They generally have a variety of competencies including creativity, ingenuity, leadership, and teamwork",
      "Includes installing firewalls, data encryption, and other security measures"},
   
    {"Ensures that data remains consistent across databases",
      "Those in this profession will need to show problem-solving and good analytical skills",
      "Most of those in this profession have had some sort of experience in the IT industry",
      "Requirements include a bachelor’s degree in computer science, computer software, electronics, IT, math, or operational research",
      "Earns an average annual salary of $75,737 CAD",
      "Informal work environment, possible for work to be carried out from home or other remote locations"},
   
    {"Those in this profession design and create programs for interactive entertainment on computers and consoles",
      "They make an average of $132,068 CAD annually",
      "Most have a bachelor's degree in computer science, software engineering or a related field",
      "Duties include creating story lines and character biographies, conducting design reviews, and documenting the design process",
      "Those in this profession need to keep up with the latest technology and trends",
      "Requirements include a bachelor's degree in software engineering, math, computer science, or computer information systems"},
    
    {"Works with clients to define projects, travels to customer sites, defines technology requirements, and installs new systems",
      "Earns an average salary of $100,992 CAD",
      "Mostly office-based, working as a member of a project team",
      "Jobs are available in many large towns and cities, work may be stressful and fast-paced",
      "Requirements include a bachelor’s degree in business studies, computer science, electronic engineering, information systems, math, or other engineering disciplines",
      "Those in this profession will need to show leadership ability, a logical approach to problem solving, the ability to learn quickly, and confident decision making"},
    
    {"Those in this profession analyze and develop programs that run corporate networks",
      "Tasks may include upgrading hardware and software to support the ideal network for the type of data communication required ",
      "Requirements include a bachelor’s degree in computer science, software engineering, or a related field",
      "Those in the profession will need the key skills of programming, debugging, and testing software",
      "Earns an average salary of $102,813 CAD",
      "Work could include debugging, encoding, testing, or installing support software. They often work in a team with collaborators from both IT and management"},
    
    {"Work includes writing well designed, testable, efficient code by using the best software development practices to create a website layout and UI by using standard HTML/CSS practices",
      "Earns an average salary of $97,908 CAD annually",
      "Requirements include a minimum of a high school degree, with a recommended bachelor’s degree in computer science or a related field",
      "Those in this profession need experience with photoshop, social media, and third party APIs",
      "Those in this profession need to be detail-oriented, and able to handle frequent feedback",
      "Tasks may include being able to troubleshoot issues quickly, and stay up-to-date on current and emerging technologies, standards, and trends"},
    
    {"Work may include configuring network hardware like servers, routers, and switches",
      "Earns an average salary of $104,858 CAD annually",
      "Key skills required for the job include analyzing and critical thinking, time management, and interpersonal skills",
      "Can work for technical consulting services, universities, insurance carriers, healthcare services, and many other professionals",
      "Requirements include a bachelor’s in IT, computer science, or other related degrees",
      "Tasks may include managing networks, preventing and fixing network problems, and supporting a number of teams and individuals"},
    
    {"Requirements include a bachelor’s in a computer science, information systems, engineering, or a related field. Work experience in a related occupation prior to working as this profession is common",
      "Those in this profession create a plan and layout for a data communication network, present the plan to management, and explain why it’s in the company’s best interest to pursue it",
      "People in this profession typically have an interest in building, thinking, and organizing",
      "Some important qualities for those in this profession are analytical skills, being detail oriented, interpersonal skills, and leadership skills",
      "Earns an average annual salary of $117,662 CAD",
      "Employment projected to grow 15 percentage from 2012 to 2022 because firms continue to expand their use of wireless and mobile networks"},
    
    {"Key skills include programming knowledge in languages such as C#, Java, and Objective-C, strong organisational skills, and mathematical aptitude",
      "Requirements include a graduate in computer science or software related degrees, there are also apprenticeships available in this field which involve on-the-job training",
      "Typical employers are Android and iOS companies, software companies, retailers, and media organisations",
      "Work includes discussing the client’s requirements and proposing a solution, developing application programming interfaces to support mobile functionality, and keeping up to date with the terminology, concepts, and best practices for coding",
      "Earns an average of $57,380 CAD annually",
      "Demand for those in this profession has never been higher, as the industry tries to keep up with surging demand"},
    
    {"Those in this profession use models to automate processes like image classification, speech recognition, and market forecasting",
      "Those in this profession must understand business objectives and develop models that help to achieve them, and manage available resources such as hardware, data, and personnel so that deadlines are met",
      "Key skills include proficiency with a deep learning framework such as TensorFlow or Keras, proficiency with basic libraries for machine learning such as scikit-learn and pandas, and expertise in visualizing and manipulating big datasets",
      "Earns an average of $137,339 CAD annually",
      "Requirements include a masters or doctoral degree in computer science or math",
      "Tasks may include using specific computer programming languages such as C++ or java, while performing computations and algorithms in sophisticated programming"},
    
    {"Those in this profession are computer experts who prepare computer in a network, which enables the computer to be able to work together and share information",
      "Requirements include a bachelor’s degree in computer systems, system design, or computer science",
      "Earns an average annual salary of $106,272 CAD",
      "Those in this profession tend to work in comfortable areas such as offices and labs, some work remotely",
      "Work may include working on a variety of networking systems for the internet, intranets, wide area networks, or local area networks",
      "Key Skills include being highly analytical, and a certain degree of interpersonal skills to interact with clients"},
    
    {"Roles include installing, upgrading and monitoring software and hardware, monitoring system performance and troubleshooting issues",
      "Those in this profession are usually the first point-of-contact for users when they experience problems with the network",
      "Requirements include a BSc/Ba in Information Technology, Computer Science or a related discipline; professional certification",
      "Earns an average salary of $57,300 CAD per year",
      "Key skills include the ability to create scripts in Python, Perl, or other languages as well as excellent communication skills",
      "Those in this profession must ensure the security and efficiency of IT infrastructure"},
    
    {"They are responsible for making sure all manufactured products meet company and industry quality standards. Running tests on products often involves using the product or conducting experiments to test the product’s durability",
      "Requirements include a bachelor’s degree in industrial engineering, or computer science",
      "Earns an average annual salary of $60,205 CAD",
      "Key skills include estimating, prioritizing, planning, and coordinating quality testing activities",
      "Those in this profession must develop and apply testing processes for new and existing products to meet client needs",
      "Those in this profession must have strong knowledge of software, tools, and processes needed for testing the assurance of a product"},
    
    {"Someone who makes value out of data, and proactively fetches information from various sources and analyzes it for better understand about how a business performs",
      "Responsible for selecting features, and using machine learning techniques with automated systems to analyze data",
      "Skills required include an excellent understanding of machine learning techniques and algorithms such as k-NN, Naive Bayes, SVM, Decision Forests, etc.",
      "Those in this profession have great communication skills and experience with data visualisation tools",
      "Earns an average annual salary of $129,300 CAD",
      "Requirements include a masters degree in computer science or mathematics, and a recommended PhD"},
    
    {"Those in this profession collaborate with Designers and Developers to create intuitive, user-friendly software",
      "The goal of those in this profession is to grasp user needs and solve problems",
      "Requirements include a BSc in Design, Computer Science, Engineering or a related field",
      "Earns an average annual salary of $75,868 CAD",
      "Those in this profession must have a clear understanding of business goals and user behavior, and be proficient in psychology and technology",
      "Key skills include the ability to clearly and effectively communicate design processes, ideas, and solutions to teams and clients"}
  };
  
  public static void main(String args[]){
    Scanner input = new Scanner(System.in);
    boolean gameLoop = true;
    
    while (gameLoop == true){
      // variable for choosing a random job 
      int randomJob = (int)Math.floor(Math.random() * 15);
      int incorrect = 0;
      String allGuesses = " ";
      
      // initial hint
      System.out.println("\nHint: "+hints[randomJob][0]+"\n");
      
      while (incorrect <= 6){
        String userGuess;
        int underScoreCount = 0;
        
        // print the letters they've guessed
        System.out.print("Letters guessed:"+allGuesses+"  |  Career:  ");
        
        // print the fill in the blanks
        for(int i=0; i < jobs[randomJob].length(); i++){
          // set variable that iterates through every letter in a career's name
          String letter = jobs[randomJob].toLowerCase().substring(i,i+1);
          
          // if there's a space in the career name, add a space in the underscores
          if(letter.equals(" ")){
            System.out.print("   ");
            
          }else if(allGuesses.toLowerCase().contains(letter)){ // if they've guessed the letter, draw the letter
            System.out.printf("%c ", jobs[randomJob].charAt(i));
            
          }else{ // otherwise, draw an underscore
            System.out.print("_ ");
            underScoreCount++;
          }
        }
        
        // if they guess the job title, they win 
        if (underScoreCount < 1){
          System.out.println("\n\nYou win!");
          break;
        }
        
        // print the man differently depending on how many they've gotten wrong 
        System.out.println(" "                           );
        System.out.println("     __________   "          );
        System.out.println("     |/      |    "          );
        System.out.print  ("     |      "                );
        System.out.println((incorrect <= 0) ? " " : "(_)"); // draw head
        System.out.print  ("     |      "                );
        System.out.print  ((incorrect <= 2) ? " " : "\\" ); // draw left arm 
        System.out.print  ((incorrect <= 1) ? " " : "|"  ); // draw upper torso
        System.out.println((incorrect <= 3) ? " " : "/"  ); // draw right arm 
        System.out.print  ("     |       "               ); 
        System.out.println((incorrect <= 1) ? " " : "|"  ); // draw lower torso
        System.out.print  ("     |      "                );
        System.out.print  ((incorrect <= 4)? " " : "/"   ); // draw left leg
        System.out.println((incorrect <= 5)? " " : " \\" ); // draw right leg 
        System.out.println("     |            "          );
        System.out.println("   __|____        "          ); 
        
        
        // quit loop if user gets 6 letters wrong 
        if (incorrect >= 6){
          System.out.println("\nGame Over!!");
          break;
        }
        
        // user input 
        System.out.println("\nGuess a letter!");
        userGuess = input.nextLine();
        
        // check for phrase
        if (userGuess.length() > 1){
          
          // if exact match, user wins
          if (userGuess.equalsIgnoreCase(jobs[randomJob])){
            System.out.println("\n\nYou win!");
            break;
            
          }else{ // otherwise, user loses
            System.out.println("\nGame Over!!"); 
            break;
          }
        }
        
        // add user guess to previous guesses 
        allGuesses += userGuess;
        
        // update incorrect count if user guesses wrong
        if (!jobs[randomJob].toLowerCase().contains(userGuess.toLowerCase())){
          incorrect++;
          if (incorrect < 6){
            System.out.println("\nHint: "+hints[randomJob][incorrect]+"\n");
          }
        }
      }
      
      // asks the user if they want to play again 
      System.out.println("\nWould you like to play again? (yes/no)");
      String userAction = input.nextLine();
      
      // if no, quit game 
      if (userAction.toLowerCase().equals("no")){
        System.out.println("Thanks for playing!");
        gameLoop = false; 
      }
    }
    input.close();
  }
}
