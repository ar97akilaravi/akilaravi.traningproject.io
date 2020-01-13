package com.cognizant.moviecruiser.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.cognizant.moviecruiser.model.Favorites;
import com.cognizant.moviecruiser.model.Movies;

public class FavoritesDaoSqlImpl implements FavoritesDao {
	public static final String ADD_TO_FAVORITES = "INSERT INTO `favorites` (`fs_us_id`, `fs_mo_id`) VALUES (?,?)";
	public static final String GET_ALL_FAVORITE_MOVIES = "SELECT mo_id,mo_title,mo_boxoffice,mo_active,mo_date_of_launch,mo_genre,mo_has_teaser FROM movies inner join favorites on fs_mo_id=mo_id where fs_us_id=?";
	public static final String GET_NO_OF_FAVORITES = "SELECT count(mo_id) as No_of_Favorites FROM movies inner join favorites on fs_mo_id=mo_id where fs_us_id=?";
	public static final String DELETE_FAVORITE = "delete from favorites where fs_us_id=? and fs_mo_id=? limit 1";

	@Override
	public void addFavoriteMovies(long userId, long moviesId) {
		Connection connection = ConnectionHandler.getConnection();
		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = connection.prepareStatement(ADD_TO_FAVORITES);
			preparedStatement.setLong(1, userId);
			preparedStatement.setLong(2, moviesId);
			int noOfRows = preparedStatement.executeUpdate();
			if (noOfRows == 0) {
				System.out.println("Insert Not Done");
			}
		} catch (SQLException e1) {
			System.out.println("SQL Exception occurs");
		} finally

		{
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				System.out.println("SQL Exception occurs");
			}
		}
	}

	@Override
	public Favorites getAllFavoriteMovies(long userId) throws FavoritesEmptyException {
		Connection connection = ConnectionHandler.getConnection();
		PreparedStatement preparedStatement = null;
		PreparedStatement preparedStatementNoOfFavorites = null;
		ArrayList<Movies> moviesList = new ArrayList<>();
		ResultSet resultSet = null;
		ResultSet resultSetNoOfFavorites = null;
		Favorites favorites = new Favorites();

		try {
			preparedStatement = connection.prepareStatement(GET_ALL_FAVORITE_MOVIES);

			preparedStatement.setLong(1, userId);
			resultSet = preparedStatement.executeQuery();
			preparedStatementNoOfFavorites = connection.prepareStatement(GET_NO_OF_FAVORITES);
			preparedStatementNoOfFavorites.setLong(1, userId);
			resultSetNoOfFavorites = preparedStatementNoOfFavorites.executeQuery();
			while (resultSet.next()) {
				Movies movies = new Movies();
				movies.setId(resultSet.getLong("mo_id"));
				movies.setTitle(resultSet.getString("mo_title"));
				movies.setGross(resultSet.getLong("mo_boxoffice"));
				movies.setActive(resultSet.getString("mo_active").equals("Yes"));
				movies.setDateOfLaunch(resultSet.getDate("mo_date_of_launch"));
				movies.setGenre(resultSet.getString("mo_genre"));
				movies.setHasTeaser(resultSet.getString("mo_has_teaser").equals("Yes"));

				moviesList.add(movies);
			}
			while (resultSetNoOfFavorites.next()) {
				favorites.setTotal(resultSetNoOfFavorites.getInt("No_of_Favorites"));
			}
			if (moviesList.size() == 0) {
				throw new FavoritesEmptyException();
			}
			favorites.setMoviesList(moviesList);
		} catch (SQLException e) {
			System.out.println("SQL Exception occurs");
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}
				if (resultSetNoOfFavorites != null) {
					resultSetNoOfFavorites.close();
				}
				if (preparedStatementNoOfFavorites != null) {
					preparedStatementNoOfFavorites.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				System.out.println("SQL Exception occurs");
			}
		}

		return favorites;

	}

	@Override
	public void removeFavoriteMovies(long userId, long moviesId) {
		Connection connection = ConnectionHandler.getConnection();
		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = connection.prepareStatement(DELETE_FAVORITE);
			preparedStatement.setLong(1, userId);
			preparedStatement.setLong(2, moviesId);
			int noOfRows = preparedStatement.executeUpdate();
			if (noOfRows == 0) {
				System.out.println("Insert Not Done");
			}
		} catch (SQLException e1) {
			System.out.println("Error");
		} finally

		{
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				System.out.println("SQL Exception occurs");
			}
		}

	}

}
