# -*- coding: utf-8 -*-
"""
Created on Wed Oct 31 15:03:11 2018

Nome: Dário Teles     TIA: 41582391
"""
"""Importação da base de dígitos manuscritos"""
from sklearn.datasets import load_digits
digits = load_digits()
digits.data.shape

"""Apresentação de alguns dígitos da base"""
import matplotlib.pyplot as plt
def plot_digits(data,n):
    fig, axes = plt.subplots(n, 10, figsize=(10, n),
                               subplot_kw={'xticks':[], 'yticks':[]},
                               gridspec_kw=dict(hspace=0.1, wspace=0.1))
    for i, ax in enumerate(axes.flat):
        ax.imshow(data[i].reshape(8, 8),
                  cmap='binary',
                  interpolation='nearest',
                  clim=(0, 16))

plot_digits(digits.data, 7)

"""Algorítimo de agrupamento K-Means"""
from sklearn.cluster import KMeans
kmeans  =  KMeans(n_clusters=10, random_state=0)
clusters  =  kmeans.fit_predict(digits.data)
kmeans.cluster_centers_.shape

"""Apresentação do agrupamento criado exibindo o centro de cada grupo"""
fig, ax = plt.subplots(2, 5, figsize=(8, 3))
centers = kmeans.cluster_centers_.reshape(10, 8, 8)
for axi, center in zip(ax.flat, centers):
    axi.set(xticks=[], yticks=[])
    axi.imshow(center, interpolation='nearest',cmap=plt.cm.binary)

"""Avaliação da acuracidade do algorítmo"""    
import numpy as np
from scipy.stats import mode
from sklearn.metrics import accuracy_score

labels = np.zeros_like(clusters)
for i in range(10):
    mask = (clusters == i)
    labels[mask] = mode(digits.target[mask])[0]
print(accuracy_score(digits.target, labels))

"""Apresentação da matriz de confusão - Agrupamento"""
from sklearn.metrics import confusion_matrix
confusion_matrix(digits.target, labels)