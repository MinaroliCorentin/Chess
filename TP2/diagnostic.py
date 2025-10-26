"""

	TP arbres de décision 

	Exercice 2

"""
import numpy as np
import matplotlib.pyplot as plt


def load_breastcancer():
	"""
	Diagnostic de tumeurs des poumons à partir d'images de biopsies 
	incluant des noyaux de cellules 
	
	Retourne Xapp, yapp, Xtest, ytest, noms_vars 
	où 
		yapp/ytest : 0 for benign; 1 for malign

		Xapp/Xtest: Ten real-valued features are computed for each cell nucleus:
		
		a) radius (mean of distances from center to points on the perimeter)
		b) texture (standard deviation of gray-scale values)
		c) perimeter
		d) area
		e) smoothness (local variation in radius lengths)
		f) compactness (perimeter^2 / area - 1.0)
		g) concavity (severity of concave portions of the contour)
		h) concave points (number of concave portions of the contour)
		i) symmetry 
		j) fractal dimension ("coastline approximation" - 1)
	"""
	data = np.load("breastcancer.npz", allow_pickle=True)
	return (data["Xtrain"] , data["ytrain"], data["Xtest"], data["ytest"], data["noms_vars"])


print("\n==== Diagnostic de cellules cancéreuses ====\n")


(Xapp, yapp, Xtest, ytest, noms_vars) = load_breastcancer()

tol = 0.05


