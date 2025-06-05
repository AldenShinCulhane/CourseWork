from ucimlrepo import fetch_ucirepo
import pandas as pd
from sklearn.preprocessing import OneHotEncoder, StandardScaler
from sklearn.cluster import KMeans
from sklearn.decomposition import PCA
import matplotlib.pyplot as plt
import seaborn as sns

# Load the dataset
credit_approval = fetch_ucirepo(id=27)

# Save the original dataset to a CSV file
X_original = credit_approval.data.features  # Extract the raw features
X_original['Target'] = credit_approval.data.targets  # Add the target column
X_original.to_csv("credit_approval_original.csv", index=False)

# Extract features (X) and targets (y)
X = credit_approval.data.features
y = credit_approval.data.targets

# Display metadata and variable information
print("Metadata:\n", credit_approval.metadata)
print("Variables:\n", credit_approval.variables)

# Handle missing values
print("Missing Values Before:\n", X.isnull().sum())  # Identify missing values

# Fill missing values in numeric columns with the mean
numeric_cols = X.select_dtypes(include=['number']).columns
X.loc[:, numeric_cols] = X[numeric_cols].fillna(X[numeric_cols].mean())

# Fill missing values in categorical columns with the mode
categorical_cols = X.select_dtypes(include=['object']).columns
for col in categorical_cols:
    X.loc[:, col] = X[col].fillna(X[col].mode()[0])

print("Missing Values After:\n", X.isnull().sum())

# Encode categorical features
encoder = OneHotEncoder(sparse_output=False, handle_unknown='ignore')  # One-hot encoding
encoded_features = encoder.fit_transform(X[categorical_cols])  # Transform categorical features

# Replace original categorical columns with encoded data
X = X.drop(columns=categorical_cols)
X_encoded = pd.DataFrame(encoded_features, columns=encoder.get_feature_names_out())
X = pd.concat([X, X_encoded], axis=1)

# Standardize numeric features
scaler = StandardScaler()
X_scaled = scaler.fit_transform(X)

# Apply k-means clustering
kmeans = KMeans(n_clusters=3, random_state=42)
kmeans.fit(X_scaled)

# Add cluster labels to the dataset
X['Cluster'] = kmeans.labels_

# Calculate mean, median, and mode statistics for each cluster
cluster_groups = X.groupby('Cluster')

# Mean statistics
mean_stats = cluster_groups.mean()
print("Mean statistics by cluster:\n", mean_stats)
mean_stats.to_csv("cluster_mean_stats.csv")

# Median statistics
median_stats = cluster_groups.median()
print("Median statistics by cluster:\n", median_stats)
median_stats.to_csv("cluster_median_stats.csv")

# Mode statistics
mode_stats = cluster_groups.agg(lambda x: x.mode().iloc[0] if not x.mode().empty else None)
print("Mode statistics by cluster:\n", mode_stats)
mode_stats.to_csv("cluster_mode_stats.csv")

# Visualize clusters using PCA
pca = PCA(n_components=2)
X_pca = pca.fit_transform(X_scaled)

plt.scatter(X_pca[:, 0], X_pca[:, 1], c=kmeans.labels_, cmap='viridis')
plt.title("Clustering Results")
plt.xlabel("PCA Component 1")
plt.ylabel("PCA Component 2")
plt.colorbar(label='Cluster')

# Save the clustered dataset to a CSV file
X.to_csv("credit_approval_clusters.csv", index=False)

# Print cluster counts
print("Cluster Counts:\n", X['Cluster'].value_counts())

# Save the PCA plot to a file
plt.savefig("clustering_results.png")
plt.show()

# Visualize cluster sizes
cluster_counts = X['Cluster'].value_counts()
cluster_counts.plot(kind='bar', color=['blue', 'green', 'orange'])
plt.title("Cluster Size Distribution")
plt.xlabel("Cluster")
plt.ylabel("Number of Points")
plt.savefig("cluster_size_distribution.png")
plt.show()

# Subset heatmap for top 10 features
top_features = mean_stats.var().sort_values(ascending=False).head(10).index
subset_stats = mean_stats[top_features]
plt.figure(figsize=(20, 8))  # Adjust figure size for subset
sns.heatmap(subset_stats.reset_index().set_index('Cluster'), cmap="YlGnBu", annot=True, fmt=".2f")
plt.title("Heatmap of Feature Means for Top 10 Features by Cluster")
plt.xticks(rotation=45, ha='right')  # Rotate x-axis labels
plt.savefig("subset_feature_heatmap.png", dpi=300)
plt.show()

# Pairplot for selected features
pairplot_data = X.copy()
pairplot_data['Cluster'] = X['Cluster'].astype(str)  # Clusters as categorical
sns.pairplot(pairplot_data, hue='Cluster', vars=['A2', 'A3', 'A8'])
plt.savefig("pairplot_clusters.png")
plt.show()
