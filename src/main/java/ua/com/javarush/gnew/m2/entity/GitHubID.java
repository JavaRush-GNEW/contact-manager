package ua.com.javarush.gnew.m2.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GitHubID {
  private String githubId;

  public GitHubID(String githubId) {
    if (githubId.length() <= 100) {
      this.githubId = githubId;
    } else {
      throw new IllegalArgumentException("GitHub ID must be between 1 and 100 characters.");
    }
  }
}
