import random

class Pantalla:
    def __init__(self, tipo: str, ancho: int, alto: int):
        self.tipo = tipo
        self.ancho = ancho
        self.alto = alto

    def __str__(self):
        return f"{self.tipo} {self.ancho} {self.alto}"


class Aplicacion:
    def __init__(self, nombre: str, tamanio: int):
        self.nombre = nombre
        self.tamanio = tamanio

    def __str__(self):
        return f"{self.nombre} {self.tamanio}"


class Laptop:
    def __init__(self, modelo: str, cant_apps: int, tipo_p: str, ancho_p: int, alto_p: int):
        self.modelo = modelo
        self.cant_apps = cant_apps
        self.pantalla = Pantalla(tipo_p, ancho_p, alto_p)
        self.apps = []
        for i in range(cant_apps):
            self.apps.append(Aplicacion(f"App{i+1}", random.randint(1, 50)))

    def agrega_app(self, app: Aplicacion):
        self.apps.append(app)
        self.cant_apps = len(self.apps)

    def eliminar_todas_apps(self):
        self.apps.clear()
        self.cant_apps = 0

    def ordenar_apps(self):
        self.apps.sort(key=lambda a: (-a.tamanio, a.nombre))

    def __str__(self):
        apps_str = ", ".join(str(app) for app in self.apps)
        return f"{self.modelo} {self.cant_apps} {self.pantalla} [{apps_str}]"


def main():
    vec = []
    n = int(input("Ingrese N: "))
    for i in range(n):
        modelo = input("Modelo: ")
        cant = int(input("Cantidad apps: "))
        tipo_p = input("Tipo pantalla: ")
        ancho_p = int(input("Ancho pantalla: "))
        alto_p = int(input("Alto pantalla: "))

        lap = Laptop(modelo, cant, tipo_p, ancho_p, alto_p)
        vec.append(lap)

    if vec:
        may = max(l.cant_apps for l in vec)
        print("Laptop con mas aplicaciones:")
        for l in vec:
            if l.cant_apps == may:
                print(l)

    modelo_x = input("Modelo X: ")
    nom_app = input("Nombre app: ")
    tam_app = int(input("Tamano app: "))

    for l in vec:
        if l.modelo == modelo_x:
            l.agrega_app(Aplicacion(nom_app, tam_app))

    ancho_y = int(input("Ancho Y: "))
    for l in vec:
        if l.pantalla.ancho == ancho_y:
            l.eliminar_todas_apps()

    print("Laptops con apps ordenadas:")
    for l in vec:
        l.ordenar_apps()
        print(l)

if __name__ == "__main__":
    main()