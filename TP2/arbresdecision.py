"""

TP arbres de décision

pour Q catégories : 
	étiquettes y dans {0, 1 ..., Q-1 }

"""
import numpy as np
import matplotlib.pyplot as plt


def categorie_majoritaire(y):
	"""
		Retourne la catégorie majoritaire parmie les étiquettes y
		ainsi que le taux d'erreurs de classification 
		si l'on affectait toutes les données à cette catégorie
	"""
	categorie = -1
	erreur = 0
	Q = max(y) + 1
	p = np.zeros(Q)
	for i in range (Q) :
		
		#Tableau de boolean 
		#Valeur avec 1 si égalité, 0 sinon
		p[i] = np.mean(y == i)
	
	categorie = np.argmax(p)
	erreur = 1 - p[categorie]
	return (categorie, erreur)
	

def gini_index(y):
	"""
		Retourne l'index de Gini pour des données étiquetées par y
	"""
	Q = max(y) + 1
	
	g = 0
	
	
	# à compléter
	
	
	return g 


def get_seuils(X, k):
	""" 
		Retourne la liste des seuils s à tester 
		pour couper la k-ème composante dans les données X
		
		s[i] = (X[i,k] - X[i+1,k] ) / 2
	"""
	Xsorted = np.sort(X[:,k])
	seuils = ( Xsorted[:-1] + Xsorted[1:] ) / 2
	seuils = np.delete (seuils, seuils == Xsorted[1:] )
	return seuils
	
	
def coupe(X, y, critere="erreur", foret_aleatoire=-1):
	""" 
		Calcule la meilleure coupe à partir des données X,y
		Retourne (k,s) avec k l'indice de la composante à couper
						 et s le seuil de coupe
						 
		critere : critère utilisé pour définir l'impureté ( "erreur" | "gini" )
		foret_aleatoire : paramètre p des forêts aleatoires
							(-1 pour un apprentissage classique) 
	"""
	m, d = X.shape # nombre, dimension des données
	
	meilleur_imp = np.inf

	coupe_k = 0
	coupe_s = 0
	
	for k in range (d) : 
		s = get_seuils(X,k)
		for seuil in s : 

			I1 = X[:,k] <= seuil

			#Nombre dans l1
			lenL1 = np.sum(I1);	
			
			I2 = X[k,:] > seuil
			
			#Nombre dans L2 
			lenL2 = np.sum(I2)
			
			FilsGauche, ErreurGauche = categorie_majoritaire(I1)
			FilsDroite, ErreurDroite = categorie_majoritaire(I2)

			impurete = lenL1 * ErreurGauche + lenL2 * ErreurDroite 
			
			if ( impurete < meilleur_imp ): 
				meilleur_imp = impurete
				coupe_k = k
				coupe_s = s
	
	return (coupe_k,coupe_s)
	
class Noeud:
	"""
		Champs :
			self.profondeur : profondeur du noeud dans l'arbre (0 pour la racine)
			self.etiquette : étiquette associée aux données affectées au noeud 
			self.feuille : True/False  
			self.composante : indice k de la variable x_k à couper
			self.seuil : seuil s où couper x_k
			self.fils1 : noeud fils pour x_k <= s
			self.fils2 : noeud fils pour x_k > s
			
		Fonctions : 
			__init__ : constructeur => crée un noeud ou une feuille 
										en optimsant le choix de la coupe
			pred : fonction de prédiction
			__str__ : pour l'affichage
	"""
	def __init__(self, profondeur, X, y, tol, critere="erreur", foret_aleatoire=-1):
		"""
			profondeur : profondeur dans l'arbre
			X, y : données affectées au noeud pour l'apprentissage
			tol  : tolérance sur l'erreur dans une feuille
			foret_aleatoire : choix de la méthode (-1 par défaut)
		
		"""

		self.profondeur = profondeur
		self.X = X
		self.tol = tol 

		self.categorie, erreur = categorie_majoritaire(y)

		if ( erreur < tol ):
			self.feuille = True
			etiquette = self.etiquette
		else :
			self.composant,self.seuil = coupe(X,y,critere="erreur", foret_aleatoire=-1)
			
			# Remplit la partie 0 à seuil 
			gauche = X[: , self.composant] <= self.seuil
			# Remplit la partie seuil + 1 à fin valeurs
			droite = X[: , self.composant] > self.seuil

			if ( self.composant < self.seuil): 
				self.fils1 = Noeud (profondeur +1,X[gauche],y[gauche],tol,critere="erreur", foret_aleatoire=-1)
				self.fils2 = Noeud (profondeur +1,X[droite],y[droite],tol,critere="erreur", foret_aleatoire=-1)
			

		# à compléter pour remplir tous les champs... 
		
		
			
	def pred(self, x):
		"""
			Pour une feuille : retourne l'étiquette de la feuille
			Pour un autre noeud : retourne la prédiction d'un des deux fils
		"""

		if ( self.feuille):
			return self.y  
		else : 
			if (x[self.composant] <= get_seuils(X, self.composant)) :
				return self.fils1.pred(x)
			else :
				return self.fils2.pred(x)

	def __str__(self):
		""" 
			Affichage d'un arbre avec print(str(arbre))
		"""
		if self.feuille:
			return "-" * self.profondeur + "* feuille de catégorie " + str(self.etiquette) 
		else:
			return "-" * self.profondeur + "* coupe x_" + str(self.composante) + " <= " + str(self.seuil) + "\n" + str(self.fils1) + "\n" + str(self.fils2)
	
	
class Arbre:
	"""
		Champs :
			self.racine : noeud racine de l'arbre
			self.noms_vars : liste des noms des variables (composantes de x) pour l'affichage
			self.critere : critère utilisé pour optimiser les coupes ("erreur" | "gini")
			self.tol : tolérance sur l'erreur dans une feuille 
			
		Fonctions :
			__init__ : constructeur + lance l'apprentissage
			pred : fonction de prédiction, retourne f(x)
			__str__ : pour l'affichage
	"""
	def __init__(self, X, y, tol, critere="erreur", noms_vars=None, foret_aleatoire=-1):		
		
		self.racine = Noeud(0, X, y, tol, critere=critere, foret_aleatoire=foret_aleatoire)
		self.noms_vars = noms_vars
		self.critere = critere
		self.tol = tol
		
	def pred(self, x):
		""" 
			Retourne ypred = f(x)
			si x est un point, ypred est une étiquette
			si x est une matrices de n points, ypred est un tableau d'étiquettes
		"""
		if x.ndim == 1:
			return self.racine.pred(x)
		else:
			ypred = np.zeros(x.shape[0], dtype=int)
			for i in range(x.shape[0]):
				ypred[i] = self.racine.pred(x[i,:])
			return ypred
		
	def __str__(self):
		"""
			Affichage avec print(str(arbre))
		"""
		s = str(self.racine)
		if self.noms_vars is not None:
			for i in range(len(noms_vars)-1,-1,-1):
				s = s.replace("x_" + str(i), self.noms_vars[i])
				
		return "Arbre (critere=" + self.critere + " ; tol = " + str(self.tol) + " ; nb de noeuds = " + str(s.count("*")) + ") \n"  + s
		



########## Foctions pour l'exercice 3 ##########		


def bootstrap(X,y):
	"""
		Retourne un nouveau jeu de données 
		de la même taille n par bootstrap de X, y
				
	"""
	n = len(y)
	
	# 1) générer une liste d'indices aléatoires entre 0 et n-1 
	indices = [] # à modifier
	
	# 2) retourner les lignes de X et y correspondant à ces indices
	return X[indices,:], y[indices]
	
def bagging(X,y, B, tol, critere="erreur"):
	""" 
		Retourne un ensemble de B arbres 
		(une liste d'arbres)
		appris par bagging
	"""
	arbres = []
		
	# à compléter...


	return arbres
	
def pred_ensemble(arbres, x):
	""" 
		Retourne f(x) pour f un ensemble d'arbres f_j
		
		avec f(x) = arrondi( z ) 
		et z = 1/B * sum_j f_j(x) 
	"""
	# initialisation de prédictions
	if x.ndim == 1:
		z = 0	# x est un seul point => une prédiction unique
	else:
		# x contient x.shape[0] points 
		#   => z est un vecteur de valeurs (une pour chaque point)
		z = np.zeros(x.shape[0])	
		
	# à compléter...
	
	# Utiliser np.rint( ... ) pour arrondir à l'entier le plus proche	
	return np.rint(z)
							
def foret_aleatoire(X,y, B, tol, critere="erreur"):


	# p = nb de variables regardées à chaque coupe
	p = int(np.sqrt(X.shape[1]))
	
	arbres = []
	
	# à compléter...
	
	
	
	return arbres

########## fin des fonctions de l'exercice 3 ######


######### Programme principal ###########

# Création des données artificielles
X = np.random.rand(100,2)
y = np.zeros(100, dtype=int)
y[X[:,0] + 0.7*X[:,1] < 0.8] = 1


# Apprentissage d'un arbre... (à compléter)

# Affichage du graphique...
plt.plot(X[y==0,0],X[y==0,1], '.b')
plt.plot(X[y==1,0],X[y==1,1], '.r')

# Coloriage d'une grille de points de test
r1 = np.linspace(0,1,40)
Xtest = np.zeros((len(r1)*len(r1),2))
i = 0
for x1 in r1:
	for x2 in r1:
		Xtest[i,:] = [x1, x2]
		i += 1


tol = 0.1
arbre = Arbre(X, y, tol)
ypred = arbre.pred(Xtest)


# à utiliser pour afficher la grille :
plt.plot(Xtest[ypred==0,0],Xtest[ypred==0,1], 'oc',alpha=0.2)
plt.plot(Xtest[ypred==1,0],Xtest[ypred==1,1], 'om', alpha=0.2)
plt.title("Partition du plan donnée par un arbre de décision")



### pour Exercice 3... ###

#print("==== bagging ====")
#B = 100
#arbres = bagging(X,y,B,tol)
#ypred = pred_ensemble(arbres, Xtest)
#plt.figure()
#plt.plot(Xtest[ypred==0,0],Xtest[ypred==0,1], 'oc',alpha=0.2)
#plt.plot(Xtest[ypred==1,0],Xtest[ypred==1,1], 'om', alpha=0.2)
#plt.title("Bagging")

#print("==== foret_aleatoire ====")
#arbres = foret_aleatoire(X,y,B,tol)
#ypred = pred_ensemble(arbres, Xtest)
#plt.figure()
#plt.plot(Xtest[ypred==0,0],Xtest[ypred==0,1], 'oc',alpha=0.2)
#plt.plot(Xtest[ypred==1,0],Xtest[ypred==1,1], 'om', alpha=0.2)
#plt.title("Forêt aléatoire")



plt.show()
