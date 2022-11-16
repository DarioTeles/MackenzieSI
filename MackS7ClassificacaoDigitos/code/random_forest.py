# -*- coding: utf-8 -*-
"""
Created on Wed Nov 14 10:29:54 2018

Nome: Dário Teles     TIA: 41582391
"""
"""Importação da base de dígitos manuscritos"""
from sklearn.datasets import load_digits
digits = load_digits()
digits.keys()

"""Apresentação de alguns dígitos da base"""
import matplotlib.pyplot as plt
# set up the figure
fig = plt.figure(figsize=(6, 6))  # figure size in inches
fig.subplots_adjust(left=0, right=1, bottom=0, top=1, hspace=0.05, wspace=0.05)

# plot the digits: each image is 8x8 pixels
for i in range(64):
    ax = fig.add_subplot(8, 8, i + 1, xticks=[], yticks=[])
    ax.imshow(digits.images[i], cmap=plt.cm.binary, interpolation='nearest')
    
    # label the image with the target value
    ax.text(0, 7, str(digits.target[i]))

"""Algorítimo de Random Forests"""
from sklearn.ensemble import RandomForestClassifier
from sklearn.cross_validation import train_test_split

Xtrain, Xtest, ytrain, ytest = train_test_split(digits.data, digits.target,
                                                random_state=0)
model = RandomForestClassifier(n_estimators=1000)
model.fit(Xtrain, ytrain)
ypred = model.predict(Xtest)

"""Relatório de Classificação"""
from sklearn import metrics
print(metrics.classification_report(ypred, ytest))

"""Apresentação da matriz de confusão"""
from sklearn.metrics import confusion_matrix
confusion_matrix(ytest, ypred)