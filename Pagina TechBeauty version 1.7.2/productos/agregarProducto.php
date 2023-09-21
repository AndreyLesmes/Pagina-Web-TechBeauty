<?php
include_once('../config/conexion.php');


$categoria = $_POST['categorias'];

// Validar si la categoría existe en la base de datos
$consulta = "SELECT id_categoria FROM categoria WHERE id_categoria = $categoria";
$resultado = mysqli_query($conexion, $consulta);

//Hacer un if para verificar si las categorias existen
if (mysqli_num_rows($resultado) == 0) {
    echo "La categoría seleccionada no existe";
} else {
    $nombre = $_POST['nombre'];
    $precio = $_POST['precio'];
    $cantidad = $_POST['cantidad'];
    $descripcion = $_POST['mensaje'];
    $unidadMedida = $_POST['unidadMedida'];
    $referencia = $_POST['referencia'];


    $consulta = "INSERT INTO producto(id_producto, id_categoria, nombre_producto, precio_producto, cantidad_producto, descripcion_producto, unidad_medida, referencia_producto) VALUES(null, '$categoria', '$nombre', '$precio', '$cantidad', '$descripcion', '$unidadMedida', '$referencia');";

    $resultado = mysqli_query($conexion, $consulta);

    if ($resultado == true) {
        header("Location: listarProductos.php");
    }
    
}
