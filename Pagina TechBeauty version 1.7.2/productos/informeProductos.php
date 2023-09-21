<?php
include_once '../config/conexion.php';

$consulta = "SELECT * FROM producto;";
header("Content-Type: application/vnd.ms-excel; charset=UTF-8");
header("Content-Disposition: attachment; filename=informe-Productos.xls");
?>

<table>
    <caption>Datos de los Productos</caption>
    <tr>
        <th>Id Producto</th>
        <th>Id Categoria</th>
        <th>Nombre</th>
        <th>Precio</th>
        <th>Cantidad</th>
        <th>Descripcion</th>
        <th>Unidades de Medida</th>
        <th>Referencia</th>
    </tr>

<?php 
$resultado = mysqli_query($conexion, $consulta);
while ($filas = mysqli_fetch_assoc($resultado)){
?>
    <tr>
        <td> <?php echo $filas['id_producto']; ?></td>
        <td> <?php echo $filas['id_categoria']; ?></td>
        <td> <?php echo $filas['nombre_producto']; ?></td>
        <td> <?php echo $filas['precio_producto']; ?></td>
        <td> <?php echo $filas['cantidad_producto']; ?></td>
        <td> <?php echo $filas['descripcion_producto']; ?></td>
        <td> <?php echo $filas['unidad_medida']; ?></td>
        <td> <?php echo $filas['referencia_producto']; ?></td>
    </tr>
<?php } ?>

</table>