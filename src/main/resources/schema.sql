--
-- Base de datos: test
--

--create database bartender;
--use bartender;
-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla arrays
--
DROP TABLE arrays;

CREATE TABLE arrays (
  id int(11) NOT NULL,
  input_array varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla arrays
--
ALTER TABLE arrays
  ADD PRIMARY KEY (id);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla arrays
--
ALTER TABLE arrays
  MODIFY id int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
