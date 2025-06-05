from sklearn import datasets 
import matplotlib.pyplot as plt
from sklearn import tree
from sklearn.metrics import accuracy_score
from sklearn.model_selection import train_test_split, GridSearchCV

############## FOR EVERYONE ##############
# Please note that the blanks are here to guide you for this first assignment, but the blanks are  
# in no way representative of the number of commands/ parameters or length of what should be inputted.

### PART 1 ###
# Scikit-Learn provides many popular datasets. The breast cancer wisconsin dataset is one of them. 
# Write code that fetches the breast cancer wisconsin dataset. 
# Hint: https://scikit-learn.org/stable/datasets/toy_dataset.html
# Hint: Make sure the data features and associated target class are returned instead of a "Bunch object".
X, y = datasets.load_breast_cancer(return_X_y=True) #(4 points) 

# Check how many instances we have in the dataset, and how many features describe these instances
print(f"There are {X.shape[0]} instances described by {X.shape[1]} features.") #(4 points)

# Create a training and test set such that the test set has 40% of the instances from the 
# complete breast cancer wisconsin dataset and that the training set has the remaining 60% of  
# the instances from the complete breast cancer wisconsin dataset, using the holdout method. 
# In addition, ensure that the training and test sets # contain approximately the same 
# percentage of instances of each target class as the complete set.
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.4, stratify=y, random_state=42) #(4 points) 

# Create a decision tree classifier. Then Train the classifier using the training dataset created earlier.
# To measure the quality of a split, using the entropy criteria.
# Ensure that nodes with less than 6 training instances are not further split
clf = tree.DecisionTreeClassifier(criterion="entropy", min_samples_split=6) #(4 points) 
clf = clf.fit(X_train, y_train) #(4 points) 

# Apply the decision tree to classify the test data.
y_pred = clf.predict(X_test) #(4 points) 

# Compute the accuracy of the classifier on the test data.
accuracy = accuracy_score(y_test, y_pred)
print(f"The accuracy of the classifier is: {accuracy * 100:.2f}%") #(2 point) 

# Visualize the tree created. Set the font size the 12 (4 points) 
plt.figure(figsize=(12, 8))
tree.plot_tree(clf, filled=True)
plt.show()

### PART 2.1 ###
# Visualize the training and test error as a function of the maximum depth of the decision tree
# Initialize 2 empty lists where you will save the training and testing accuracies 
# as we iterate through the different decision tree depth options.
trainAccuracy = []  #(1 point) 
testAccuracy = [] #(1 point) 

# Use the range function to create different depths options, ranging from 1 to 15, for the decision trees
depthOptions = range(1, 16) #(1 point) 

for depth in depthOptions: #(1 point) 
    # Use a decision tree classifier that still measures the quality of a split using the entropy criteria.
    # Also, ensure that nodes with less than 6 training instances are not further split
    cltree = tree.DecisionTreeClassifier(criterion='entropy', max_depth=depth, min_samples_split=6) #(1 point) 
    
    # Decision tree training
    cltree = cltree.fit(X_train, y_train) #(1 point) 
    
    # Training error
    y_predTrain = cltree.predict(X_train) #(1 point) 
    
    # Testing error
    y_predTest = cltree.predict(X_test) #(1 point) 
    
    # Training accuracy
    trainAccuracy.append(accuracy_score(y_train, y_predTrain)) #(1 point) 
    
    # Testing accuracy
    testAccuracy.append(accuracy_score(y_test, y_predTest)) #(1 point) 

# Plot of training and test accuracies vs the tree depths (use different markers of different colors)
plt.figure(figsize=(10, 6))
plt.plot(depthOptions, trainAccuracy, 'o-', label='Training Accuracy', color='blue')  #(3 points) 
plt.plot(depthOptions, testAccuracy, 's-', label='Test Accuracy', color='orange')  #(3 points) 

plt.legend(['Training Accuracy', 'Test Accuracy'])  # add a legend for the training accuracy and test accuracy (1 point) 
plt.xlabel('Tree Depth')  # name the horizontal axis 'Tree Depth' (1 point) 
plt.ylabel('Classifier Accuracy')  # name the vertical axis 'Classifier Accuracy' (1 point) 

plt.grid(True)
plt.show()

# Fill out the following blanks: #(4 points (2 points per blank)) 

""" 
According to the test error, the best model to select is when the maximum depth is equal to 3, approximately. 
But, we should not use select the hyperparameters of our model using the test data, because doing so could 
lead to overfitting the model to this specific test set, reducing its ability to generalize to unseen data.
"""

### PART 2.2 ###
# Use sklearn's GridSearchCV function to perform an exhaustive search to find the best tree depth and the minimum number of samples to split a node
# Hint: https://scikit-learn.org/stable/modules/generated/sklearn.model_selection.GridSearchCV.html
# Define the parameters to be optimized: the max depth of the tree and the minimum number of samples to split a node
parameters = {'max_depth': range(1, 21), 'min_samples_split': range(2, 11)} #(6 points)

# We will still grow a decision tree classifier by measuring the quality of a split using the entropy criteria. 
clf = tree.DecisionTreeClassifier(criterion='entropy') #(6 points)

grid_search = GridSearchCV(clf, parameters, cv=5) 
grid_search.fit(X_train, y_train) #(4 points)

tree_model = grid_search.best_estimator_ #(4 points)

print("The maximum depth of the tree is", tree_model.max_depth, 
      'and the minimum number of samples required to split a node is', tree_model.min_samples_split) #(6 points)

# The best model is tree_model. Visualize that decision tree (tree_model). Set the font size the 12 
plt.figure(figsize=(20, 12))
_ = tree.plot_tree(tree_model, filled=True, fontsize=12)  #(4 points)
plt.show()

# Fill out the following blank: #(2 points)


""" 
This method for tuning the hyperparameters of our model is acceptable, because GridSearchCV 
uses cross-validation, which allows the model to be evaluated on multiple subsets of the data, 
reducing the risk of overfitting to any particular training or validation split. 
"""

# Explain below what is tenfold Stratified cross-validation?  #(4 points)
"""
Tenfold stratified cross-validation is a technique where the dataset is divided into 10 equal folds. 
Each fold is used once as the test set while the remaining 9 folds are used for training. This process 
repeats 10 times, and the average performance across all folds is reported. The "stratified" part ensures 
that the class distribution (e.g., benign vs malignant) in each fold is consistent with the distribution 
in the overall dataset, leading to more representative and balanced splits.
"""

