<?php
include_once('../config/conexion.php');

$id = $_REQUEST['Id'];

$consulta = "SELECT * FROM producto WHERE id_producto = '$id'";
$resultado = mysqli_query($conexion, $consulta);
$filas = mysqli_fetch_array($resultado);

?>

<!DOCTYPE html>
<html>

<head>
    <link rel="stylesheet" href="/CSS/registrar_Producto.css">
    <link rel="icon" type="image/png" href="img/logo_barra.png">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.0/font/bootstrap-icons.css">
    <script src="../java_script/menu_inve.js"></script>
    <title>Editar Productos</title>
</head>

<body>
<div class="container">
        <div class="menu-dashboard">
            <div class="top-menu">
                <div class="logo">
                    <img src="./Img/logo.png" alt="logo">
                    <span>TechBeauty</span>
                </div>
                <div class="toggle">
                    <i class="bi bi-list"></i>
                </div>
            </div>

            <div class="menu">
                <div class="enlace">
                    <i class="bi bi-person"></i>
                    <span>Usuario</span>
                </div>
            </div>
            <div class="menu">
                <div class="enlace">
                    <i class="bi bi-bag-plus-fill"></i>
                    <a href="registrar_Producto.php">Registrar Productos</a>
                </div>
            </div>
            <div class="menu">
                <div class="enlace">
                    <i class="bi bi-search"></i>
                    <a href="listarProductos.php">Buscar Productos</a>
                </div>
            </div>
            <div class="menu">
                <div class="enlace">
                    <i class="bi bi-bag-check-fill"></i>
                    <span>Ventas Realizadas</span>
                </div>
            </div>
            <div class="menu">
                <div class="enlace">
                    <i class="bi bi-card-text"></i>
                    <span>Datos Personales</span>
                </div>
            </div>
            <div class="menu">
                <div class="CS">
                    <div class="cerrarSesion">
                        <i class="bi bi-power"></i>
                        <span>Cerrar Sesión</span>
                    </div>
                </div>
            </div>
        </div>
        <main>
            <div class="content-container">
            <h1>Editar Productos</h1>
            <form class="registro_productos" action="editar.php" method="post">
                <div class="registro">
                    <div class="box">
                        <input type="hidden" name="Id" value="<?php echo $filas['id_producto']; ?>">
                        <!--Se hace un if para que en caso de que la opcion sea la que esta seleccionada, se cambie su valor a selected -->
                        <label for="categoria">Categoria: </label>
                        <select class="categorias" name="categorias" id="categorias">
                            <option value="0">Seleccione la categoria</option>
                            <option value="1" <?php if ($filas['id_categoria'] == 1) echo 'selected="selected"'; ?>>Maquillaje</option>
                            <option value="2" <?php if ($filas['id_categoria'] == 2) echo 'selected="selected"'; ?>>Cuidado Facial</option>
                            <option value="3" <?php if ($filas['id_categoria'] == 3) echo 'selected="selected"'; ?>>Cuidado Corporal</option>
                            <option value="4" <?php if ($filas['id_categoria'] == 4) echo 'selected="selected"'; ?>>Esmaltes</option>
                            <option value="5" <?php if ($filas['id_categoria'] == 5) echo 'selected="selected"'; ?>>Cabello</option>
                        </select>
                        <br><br>
                        <label for="nombre">Nombre: </label>
                        <input class="control" type="text" name="nombre" id="nombre" placeholder="Escriba el nombre del producto: " value="<?php echo $filas['nombre_producto'] ?>">
                        <br><br>
                        <label for="Precio">Precio: </label>
                        <input class="control" type="number" name="precio" id="precio" placeholder="Ingrese el Precio del producto: " required min="1" step="1" value="<?php echo $filas['precio_producto'] ?>">
                        <br><br>
                        <label for="cantidad">Cantidad: </label>
                        <input class="control" type="number" name="cantidad" id="cantidad" placeholder="Ingrese la cantidad del producto: " required min="1" max="500" step="1" value="<?php echo $filas['cantidad_producto'] ?>">
                        <br><br>
                        <br>
                        <a class="botonRegresar" href="listarProductos.php">Regresar</a>
                    </div>
                    <div class="box">
                        <label for="descripcion">Descripción: </label><br>
                        <textarea class="control" name="mensaje" id="mensaje" rows="3" cols="25"><?php echo $filas['descripcion_producto'] ?></textarea>
                        <br><br>
                        <label for="und_med">Unidad de Medida: </label>
                        <input class="control" type="text" name="unidadMedida" id="unidadMedida" placeholder="Ingrese la unidad de medida: " required value="<?php echo $filas['unidad_medida'] ?>">
                        <br><br>
                        <label for="referencia">Referencia: </label>
                        <input class="control" type="text" name="referencia" id="referencia" placeholder="Ingrese la referencia del producto: " required value="<?php echo $filas['referencia_producto'] ?>">
                        <br><br>
                        <input type="submit" name="submit" value="Editar Producto">

                    </div>
                </div>

            </form>
        </div>
    </main>
    </div>
</body>

</html>