import numpy as np 
import time as time 

# Tirez un nombre selon une loi uniforme entre 0 et 1  
alea = np.random.rand()
print( "La valeur est ", alea )

# Tirez 10 nombres aléatoires indépendamment selon une loi uniforme entre 0 et 1 
alea2 = np.random.rand(10)
print(" La valeur en rand 10 est ", alea2)

# Tirez un nombre entier aléatoire selon une loi uniforme entre 9 et n-1 
alea3 = np.random.randint(0,15)
print("La Valeur entre 0 et n-1 est ", alea3)

# Tirez 10 nombres entiers aléatoires selon une loi uniforme entre 0 et n-1 
alea4 = np.random.randint(0,15,size = 10)
print( "Tirage de 10 nombres aléatoires", alea4 )

#Créez la fonction def X qui retourne un tirage de la variable aléatoire X de loi uniforme 
def X(): 
    x = np.random.randint(0,6)
    return x 

alea5 = X()
print(" Tirage de 6 nombres ", alea5)


# Implementez une fonction  def esperance ( VA, temps)  qui calcule en un temps fixe ( en secondes ) une approximation de l'esperance d'une variable aleatoire de loi inconnue )

def esperance(valeurs,temps):
  
    depart = time.time()
    moyenne = 0 
    cmpt = 0 

    while  (( time.time() - depart ) < temps ): 
        moyenne += valeurs 
        cmpt += 1

    moyenne = moyenne / cmpt
    return moyenne

random = X()
print (" Esperance d'une fonction ", esperance(random,2))

# Creez la fonction X2 qui simule le tirage d'une variable X2 de loi donnée par 
import numpy as np

def X2():
    alea = np.random.random()  # un nombre entre 0 et 1
    if alea < 0.3:
        return 0
    elif alea < 0.5:
        return 1
    else:
        return 3

# Test
print("Question 7 : Tirage d'une variable", X2())



def esperance_X2_carre_empirique(n):
    total = 0
    for i in range(n):
        xi = X2()
        total += xi**2
    return total / n

E_X2_carre_emp = esperance_X2_carre_empirique(10000)
E_X2_carre_exact = 0**2 * 0.3 + 1**2 * 0.2 + 3**2 * 0.5

print("Question 8. Estimation empirique de E[X₂²] :", E_X2_carre_emp)
print("Question 8. Valeur exacte de E[X₂²] :", E_X2_carre_exact)




#Estimez l'esperance de X2 empiriquement² , puis verifier par le calcul exact².

def esperance_X2_carre_empirique(n):
    total = 0
    for i in range(n):
        xi = X2()
        total += xi**2
    return total / n

E_X2_carre_emp = esperance_X2_carre_empirique(10000)
E_X2_carre_exact = 0**2 * 0.3 + 1**2 * 0.2 + 3**2 * 0.5

print("Question 9 Estimation empirique de E[X₂²] :", E_X2_carre_emp)
print("Question 9 Valeur exacte de E[X₂²] :", E_X2_carre_exact)


def proba(valeur, temps) : 

    debut = time.time() # Debut

    compteur = 0.0
    reussite = 0.0

    while time.time() - debut < temps: # Duree de l'espérience

        VA = X()
        compteur = compteur + 1
        if (VA == valeur):
            reussite = reussite + 1 

    return float(reussite / compteur) 

print("Question 10 :", proba(1, 3))


#Couple aléatoire


def XY():

    x = -1 
    y = -1

    tirage = np.random.randint(0, 10)  

    if tirage == 0:
        x, y = 0, 0       # 0.1 
    elif tirage >= 1 and tirage <= 4:
        x, y = 0, 1       # 0.4
    elif tirage > 4 and tirage <= 7:
        x, y = 1, 0       # 0.3
    else:
        x, y = 1, 1       # 0.2

    return x, y


print("Question 1b, valuer de XY = ",XY())


def estimation_X_sachant_Y(a, b, temps=2):
    debut = time.time()
    total = 0
    succes_XsachantY = 0

    while time.time() - debut < temps:
        x, y = XY()
        if y == b : 
            total += 1
            if x == a : 
                succes_XsachantY += 1

    return succes_XsachantY / total 

# Exemple d'utilisation :
print("Question 2b : Estimation de P(X=0 | Y=0) :", estimation_X_sachant_Y(0, 0, 2))
print("Question 2b : Estimation de P(X=1 | Y=0) :", estimation_X_sachant_Y(1, 0, 2))
print("Question 2b : Estimation de P(X=0 | Y=1) :", estimation_X_sachant_Y(0, 1, 2))
print("Question 2b : Estimation de P(X=1 | Y=1) :", estimation_X_sachant_Y(1, 1, 2))