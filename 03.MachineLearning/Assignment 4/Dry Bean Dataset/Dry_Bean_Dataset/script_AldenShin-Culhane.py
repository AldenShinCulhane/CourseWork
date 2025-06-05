from ucimlrepo import fetch_ucirepo
from sklearn.preprocessing import StandardScaler
from sklearn.decomposition import PCA
from scipy.cluster.hierarchy import dendrogram, linkage, fcluster
import pandas as pd
import matplotlib.pyplot as plt
import seaborn as sns

# Load the Dry Bean dataset
dry_bean = fetch_ucirepo(id=602)
X_original = dry_bean.data.features
X_original['Class'] = dry_bean.data.targets
X_original.to_csv("dry_bean_original.csv", index=False)
print("Dataset Summary:")
print(dry_bean.metadata)

# Extract numeric features for clustering
X_numeric = X_original.drop(columns=['Class'])
scaler = StandardScaler()
X_scaled = scaler.fit_transform(X_numeric)

# PCA for dimensionality reduction
pca = PCA(n_components=2)
X_pca = pca.fit_transform(X_scaled)

# PCA loadings: what each principal component is made of
pca2 = PCA(n_components=2)
X_pca = pca2.fit_transform(X_scaled)

loadings = pd.DataFrame(
    pca2.components_,
    columns=X_numeric.columns,
    index=[f'PC{i+1}' for i in range(pca2.n_components_)]
)
print("PCA Component Loadings:\n", loadings)

# Plot PCA loadings
plt.figure(figsize=(10, 6))
loadings.T.plot(kind='bar', figsize=(12, 6))
plt.title("PCA Component Loadings")
plt.xlabel("Features")
plt.ylabel("Loading Value")
plt.xticks(rotation=45, ha='right')
plt.legend(title='Principal Components')
plt.tight_layout()
plt.show()

# Perform hierarchical clustering
linked = linkage(X_scaled, method='ward')

# Plot dendrogram
plt.figure(figsize=(10, 7))
dendrogram(linked, truncate_mode='lastp', p=30, leaf_rotation=45, leaf_font_size=10, show_contracted=True)
plt.title('Hierarchical Clustering Dendrogram')
plt.xlabel('Cluster Size')
plt.ylabel('Distance')
plt.tight_layout()
plt.show()

# Assign cluster labels based on a distance threshold or fixed number of clusters
n_clusters = 4
labels = fcluster(linked, n_clusters, criterion='maxclust')
X_numeric['Cluster'] = labels
X_numeric.to_csv("hierarchical_clusters.csv", index=False)

# PCA scatter plot with hierarchical cluster labels
plt.figure(figsize=(8, 6))
sns.scatterplot(x=X_pca[:, 0], y=X_pca[:, 1], hue=labels, palette='viridis', legend='full')
plt.title("Hierarchical Clustering Results (PCA Visualization)")
plt.xlabel("PCA Component 1")
plt.ylabel("PCA Component 2")
plt.legend(title='Cluster', loc='upper right')
plt.tight_layout()
plt.show()

# Cluster size distribution
cluster_counts = pd.Series(labels).value_counts()
cluster_counts.plot(kind='bar', color=['blue', 'green', 'orange', 'red', 'purple', 'cyan', 'yellow'])
plt.title("Cluster Size Distribution (Hierarchical Clustering)")
plt.xlabel("Cluster")
plt.ylabel("Number of Points")
plt.tight_layout()
plt.show()

# Calculate mean, median, and mode statistics for each cluster
cluster_groups = X_numeric.groupby('Cluster')

mean_stats = cluster_groups.mean()
mean_stats.to_csv("hierarchical_cluster_mean_stats.csv")

median_stats = cluster_groups.median()
median_stats.to_csv("hierarchical_cluster_median_stats.csv")

mode_stats = cluster_groups.agg(lambda x: x.mode().iloc[0] if not x.mode().empty else None)
mode_stats.to_csv("hierarchical_cluster_mode_stats.csv")

# Heatmap of mean statistics for selected features
top_features = mean_stats.var().sort_values(ascending=False).head(10).index
subset_stats = mean_stats[top_features]
plt.figure(figsize=(20, 8))
sns.heatmap(subset_stats.reset_index().set_index('Cluster'), cmap="YlGnBu", annot=True, fmt=".2f")
plt.title("Heatmap of Feature Means for Selected Features by Cluster")
plt.xticks(rotation=45, ha='right')
plt.tight_layout()
plt.show()

# Pairplot for selected features
pairplot_data = X_numeric.copy()
pairplot_data['Cluster'] = X_numeric['Cluster'].astype(str)
sns.pairplot(pairplot_data, hue='Cluster', vars=['Area', 'Perimeter', 'AspectRatio'])
plt.tight_layout()
plt.show()
