from flask import Flask, request, jsonify
import pandas as pd
import joblib
from sklearn.metrics.pairwise import cosine_similarity

# Load your data
article_data = pd.read_csv('hasil_akhir.csv')

# Load the pre-trained TF-IDF vectorizer and matrix
vectorizer = joblib.load('tfidf_vectorizer.joblib')
tfidf_matrix = joblib.load('tfidf_matrix.joblib')

# Initialize the Flask application
app = Flask(__name__)

# Function to generate recommendations
def get_recommendations(article_index):
    cosine_similarities = cosine_similarity(tfidf_matrix, tfidf_matrix[article_index]).flatten()

    # Get indices of similar articles
    similar_indices = cosine_similarities.argsort()[-6:][::-1]  # Adjust the number as needed

    # Exclude the original article and select top recommendations
    similar_indices = [i for i in similar_indices if i != article_index]
    
    # Get details of the recommended articles
    recommended_articles = article_data.iloc[similar_indices]

    return recommended_articles.to_dict(orient='records')

# Define the route for recommendations
@app.route('/recommend', methods=['GET'])
def recommend():
    # Get the article index from the request arguments
    article_index = request.args.get('index', default=0, type=int)
    
    # Generate recommendations
    recommendations = get_recommendations(article_index)
    
    # Return the recommendations as a JSON response
    return jsonify(recommendations)

# Run the app
if __name__ == '__main__':
    app.run(debug=True)